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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.subro.soundmaking.db.Loginuser;

/**
 *
 * @author subro
 */
@WebFilter(filterName = "GroupAuthenticationFilter", urlPatterns = {"/user/menu/manager"})
public class GroupAuthenticationFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(GroupAuthenticationFilter.class);
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) request).getSession();

        //本当は UserAuthenticationFilterを先に実行したいのだがフィルターの順序を指定できないので
        if (session == null || session.getAttribute("loginuser") == null) {
            ((HttpServletResponse) response).sendRedirect("/SoundMaking/login");
        } else {
            Loginuser loginuser = (Loginuser) session.getAttribute("loginuser");
            String groupname = loginuser.getGroupname();

            if (groupname.equals("admin")) {
                chain.doFilter(request, response);
            } else {
                logger.info("adminではないユーザー" + loginuser.getUsername() + "が権限のない画面にアクセスしようとしました");
                ((HttpServletResponse) response).sendRedirect("/SoundMaking/user/menu");
            }
        }
    }

}
