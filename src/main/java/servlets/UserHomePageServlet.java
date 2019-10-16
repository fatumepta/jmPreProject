package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserHomePageServlet", urlPatterns = "/user")
public class UserHomePageServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(200);
        System.out.println(request.getContextPath());
        request.getRequestDispatcher("/userview/userHome.jsp").forward(request, response);
    }
}
