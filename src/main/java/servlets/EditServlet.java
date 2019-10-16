package servlets;

import models.User;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "EditServlet", urlPatterns = "/edit")
public class EditServlet extends HttpServlet {
    private UserService service = UserService.getInstance();
    private User userToBeEdited;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(200);
        userToBeEdited = service.get(Long.parseLong(request.getParameter("id")));
        request.setAttribute("name", userToBeEdited.getName());
        request.setAttribute("login", userToBeEdited.getLogin());
        request.setAttribute("password", userToBeEdited.getPassword());
        request.setAttribute("role", userToBeEdited.getRole());

        request.getRequestDispatcher("/adminview/editUser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(200);
        service.edit(userToBeEdited, new String[]{
                request.getParameter("name"),
                request.getParameter("login"),
                request.getParameter("password"),
                request.getParameter("role")});

        response.sendRedirect("/users");
    }
}
