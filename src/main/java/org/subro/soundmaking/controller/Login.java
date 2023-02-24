/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package org.subro.soundmaking.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.subro.soundmaking.db.Loginuser;
import org.subro.soundmaking.db.LoginuserService;
import org.subro.soundmaking.sound.EffectorBoard;
import org.subro.soundmaking.user.EncriptPassword;
import org.subro.soundmaking.user.OriginalEncriptPassword;

/**
 *
 * @author subro
 */
@WebServlet(name = "Login", urlPatterns = {"/login"})
public class Login extends HttpServlet {
    
    private static final Logger logger = LoggerFactory.getLogger(Login.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 画面から以外の不正なアクセスを想定
        // ログイン画面を再表示へ
        if (request.getParameter("username") == null || request.getParameter("password") == null) {
            logger.info("ログイン画面を通さない不正なアクセスがあったようです");

            // ログイン画面表示
            doGet(request, response);
        } // ユーザー名かパスワードが空(スペースだけも空とする)の場合
        // ログイン画面にエラーメッセージを持たせてログイン画面再表示へ
        else if (request.getParameter("username").trim().equals("") || request.getParameter("password").trim().equals("")) {

            request.setAttribute("errmsg", "ユーザー名とパスワードは空にできません。");

            // ログイン画面表示
            doGet(request, response);
        } // 正常にログインしようとしている場合
        else {
            // ログイン画面で入力されたパスワード
            String requestUsername = request.getParameter("username").trim();
            String requestPassword = request.getParameter("password").trim();

            // データベースからユーザー名で指定されたユーザーを取得
            LoginuserService loginuserService = new LoginuserService();
            Loginuser loginuser = loginuserService.getLoginuserByUsername(requestUsername);

            // データベースから対象のユーザーを正常に取得できた場合
            if (loginuser != null) {

                // パスワード暗号化ロジック取得
                EncriptPassword encriptPassword = new OriginalEncriptPassword();
                // データベースに入っていたパスワード取得してデコード
                String storedPassword = encriptPassword.getPassword(loginuser.getPassword());

                // パスワードが正しい場合
                if (requestPassword.equals(storedPassword)) {

                    HttpSession session = request.getSession();

                    session.setAttribute("login", true);
                    session.setAttribute("loginuser", loginuser);
                    session.setAttribute("EffectorBoard", new EffectorBoard(5));

                    logger.info(requestUsername + " がログインしました");

                    // トップメニューへリダイレクト
                    response.sendRedirect("/SoundMaking/menu");
                } // パスワードが誤っている場合
                else {
                    request.setAttribute("errmsg", "ログインに失敗しました。");
                    logger.info(requestUsername + " がログインに失敗しました");
                    // ログイン画面表示
                    doGet(request, response);
                }
            } // データベースからのログインユーザー取得が0件だったか2件以上あった場合
            else {
                request.setAttribute("errmsg", "ログインに失敗しました。");
                logger.info(requestUsername + " がログインに失敗しました");
                // ログイン画面表示
                doGet(request, response);
            }
        }
    }
}
