package servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jdbc.dbWorker.Repository;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        Repository r = new Repository();
        try {
            if (r.isAdminUser(request.getParameter("password"), request.getParameter("name"))) {
                session.setAttribute("type", "admin");
                out.println("admin");
            } else {
                session.setAttribute("type", "user");
                out.println("user");
            }
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(request, response);
    }
}
