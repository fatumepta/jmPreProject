package servlets.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter(filterName = "LogInFilter", urlPatterns = "/*")
public class LogInFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();

        // if user logged in
        if (session.getAttribute("user") != null) {

            // if logged in user tries to get /login page (index.jsp)
            if (request.getRequestURI().equals("/login")) {
                response.sendRedirect("/user");
            } else {
                chain.doFilter(request, response);
            }

            // if user didn't log in
        } else {
            request.getRequestDispatcher("/login").forward(request, response);
        }
    }

    public void init(FilterConfig config) throws ServletException {
    }
}
