package filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jdbc.dbWorker.Repository;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class LoginFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {

    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        Repository r = new Repository();
        try {
            if (r.isUser(password, name)) {
                chain.doFilter(request, response);
            } else {
                PrintWriter out = response.getWriter();
                out.print("Incorrect username or password");
            }
        } catch (ClassNotFoundException | SQLException | IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(e);
        }

    }
}
