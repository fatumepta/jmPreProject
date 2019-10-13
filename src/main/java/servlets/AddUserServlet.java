package servlets;

import models.User;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "AddUserServlet", urlPatterns = "/add")
public class AddUserServlet extends HttpServlet {
    private UserService service = UserService.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(200);

        String login = request.getParameter("login");
        if (service.add(
                new User(
                        request.getParameter("name"),
                        login,
                        request.getParameter("password"),
                        request.getParameter("role")))) {
            response.sendRedirect("/users");
        } else {
            request.setAttribute("message", "user with login [" + login + "] already exists!");
            doGet(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(200);
        request.getRequestDispatcher("view/addUser.jsp").forward(request, response);
    }
}
