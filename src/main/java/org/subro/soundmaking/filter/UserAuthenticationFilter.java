/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */
package org.subro.soundmaking.filter;

import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author subro
 */
@WebFilter(filterName = "UserAuthenticationFilter", urlPatterns = {"/*"})
public class UserAuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpSession session = ((HttpServletRequest) request).getSession();

        // 本当は @WebFilterで除外パスを設定できれば良かったがやり方が分からず
        // 除外URIを個別にハードコードして次のフィルタに渡すようにした
        String url = ((HttpServletRequest) request).getServletPath();
        if (url.equals("/healthcheck") || url.equals("/login") || url.equals("/css")) {
            chain.doFilter(request, response);
        } // 除外URIではないところへはログインしていなければログイン画面に飛ばす
        else {
            // そもそもセッションがまだない場合はログインさせる
            if (session == null) {
                ((HttpServletResponse) response).sendRedirect("/SoundMaking/login");
            } // セッションはある場合
            else {
                // ログインしている場合にはセッションに"login"がある
                Object loginCheck = session.getAttribute("login");

                // ログインていない場合はログイン画面へ
                if (loginCheck == null) {
                    ((HttpServletResponse) response).sendRedirect("/SoundMaking/login");
                } // ログインしている場合は次のフィルターへ
                else {
                    chain.doFilter(request, response);
                }
            }
        }
    }

}
