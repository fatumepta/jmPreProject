package servlets;

import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "ShowUsersServlet", urlPatterns = "/users")
public class ShowUsersServlet extends HttpServlet {
    private UserService service = UserService.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(200);
        request.setAttribute("users", service.getAll());
        request.getRequestDispatcher("/adminview/showUsers.jsp").forward(request, response);
    }
}
