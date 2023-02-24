/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package org.subro.soundmaking.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.subro.soundmaking.db.Loginuser;
import org.subro.soundmaking.db.LoginuserService;
import org.subro.soundmaking.user.EncriptPassword;
import org.subro.soundmaking.user.OriginalEncriptPassword;


/**
 *
 * @author subro
 */
@WebServlet(name = "UserManager", urlPatterns = {"/user/menu/manager"})
public class UserManager extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(UserManager.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserManager() {
        super();
    }

    /**
     * @param request
     * @param response
     * @throws jakarta.servlet.ServletException
     * @throws java.io.IOException
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LoginuserService loginuserService = new LoginuserService();
        List<Loginuser> loginusers = loginuserService.getLoginuserAll();

        request.setAttribute("loginusers", loginusers);
        request.getRequestDispatcher("/WEB-INF/view/usermanager.jsp").forward(request, response);
    }

    /**
     * @param request
     * @param response
     * @throws jakarta.servlet.ServletException
     * @throws java.io.IOException
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = (String) request.getParameter("action");
        String id = (String) request.getParameter("id");        // Stringなので注意

        HttpSession session = request.getSession();
        Loginuser operator = (Loginuser) session.getAttribute("loginuser");

        LoginuserService loginuserService = new LoginuserService();

        // usermanager.jspで編集ボタンを押した場合
        if (action.equals("update")) {
            request.setAttribute("action", "update");
            request.setAttribute("id", id);
        } // usermanager.jspで決定ボタンを押した場合
        else if (action.equals("confirm")) {

            String groupname = (String) request.getParameter("groupname");

            if (groupname.length() > 5) {
                request.setAttribute("err", "グループ名は5文字までです");
            } else {
                Loginuser loginuser = loginuserService.getLoginuserById(Integer.parseInt(id));
                loginuser.setGroupname(groupname);
                loginuserService.updateLoginuser(loginuser);
                logger.info(operator.getUsername() + " により " + loginuser.getUsername() + " のグループが変更されました");
            }
        } // usermanager.jspで削除ボタンを押した場合
        else if (action.equals("delete")) {
            Loginuser loginuser = loginuserService.getLoginuserById(Integer.parseInt(id));
            loginuserService.deleteLoginuser(Integer.parseInt(id));
            logger.info(operator.getUsername() + " により " + loginuser.getUsername() + " が削除されました");
        } // usermanager.jspで新規登録ボタンを押した場合
        else if (action.equals("insert")) {

            String username = (String) request.getParameter("username");
            String password = (String) request.getParameter("password");
            String groupname = (String) request.getParameter("groupname");

            // 入力エラーチェック
            if (username.equals("")) {
                request.setAttribute("err", "ユーザー名を空にすることはできません");
            } else if (username.length() > 20) {
                request.setAttribute("err", "ユーザー名は20文字までです");
            } else if (password.equals("")) {
                request.setAttribute("err", "パスワードを空にすることはできません");
            } else if (password.length() > 20) {
                request.setAttribute("err", "パスワードは20文字までです");
            } else if (groupname.equals("")) {
                request.setAttribute("err", "グループ名を空にすることはできません");
            } else if (groupname.length() > 5) {
                request.setAttribute("err", "グループ名は5文字までです");
            } else if (loginuserService.checkLoginuserByUsername(username)) {
                request.setAttribute("err", "そのユーザーは既に存在しています");
            } // DBに新規登録
            else {
                EncriptPassword encriptPassword = new OriginalEncriptPassword();
                loginuserService.createLoginuser(username, encriptPassword.getEncriptedPassword(password), groupname);
                logger.info(operator.getUsername() + " により " + username + " が新規作成されました");
            }
        } else {
            // やることなし
        }

        doGet(request, response);
    }

}
