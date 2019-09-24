package servlets;

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
        String login = request.getParameter("login");
        if (service.addUser(request.getParameter("name"), login, request.getParameter("password"))) {
            request.setAttribute("message", login + " registered successfully!");
        } else {
            request.setAttribute("message", "user with login [" + login +  "] already exists!");
        }

        response.sendRedirect("/users");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("view/addUser.jsp").forward(request, response);
    }
}
