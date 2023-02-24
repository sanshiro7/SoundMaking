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
@WebServlet(name = "ChangePassword", urlPatterns = {"/user/menu/changepassword"})
public class ChangePassword extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(ChangePassword.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePassword() {
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
        request.getRequestDispatcher("/WEB-INF/view/changepassword.jsp").forward(request, response);
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

        String password1 = (String) request.getParameter("newpassword1").trim();
        String password2 = (String) request.getParameter("newpassword2").trim();

        if ("".equals(password1) || "".equals(password2)) {
            request.setAttribute("err", "パスワードを空にはできません。");
        } else if (password1.length() > 20) {
            request.setAttribute("err", "パスワードは20文字までです。");
        } else if (!password1.equals(password2)) {
            request.setAttribute("err", "パスワード(確認)が同じではありません。");
        } else {
            // パスワード暗号化ロジック
            EncriptPassword encriptPassword = new OriginalEncriptPassword();
            //入力されたパスワードを暗号化
            String encriptedPassword = encriptPassword.getEncriptedPassword(password1);

            // セッションから loginuser インスタンスを取り出し
            HttpSession session = request.getSession();
            Loginuser loginuser = (Loginuser) session.getAttribute("loginuser");

            // loginuserテーブルの DAO
            LoginuserService loginuserService = new LoginuserService();

            loginuser.setPassword(encriptedPassword);
            loginuserService.updateLoginuser(loginuser);
            logger.info(loginuser.getUsername() + " がパスワードを変更しました");
            request.setAttribute("msg", "パスワードを変更しました。");
        }

        doGet(request, response);
    }

}
