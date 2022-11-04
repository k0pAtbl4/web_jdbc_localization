package filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jdbc.dbWorker.Repository;

import java.io.IOException;
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

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        Repository r = new Repository();
        try {
            if (r.isUser(password, name)) {
                chain.doFilter(request, response);
            } else {
                //PrintWriter out = response.getWriter();
                //out.print("Incorrect username or password");
                httpRequest.setAttribute("errorMessage", true);
                httpRequest.getRequestDispatcher("/forJsp/errorLogin.jsp")
                        .forward(httpRequest, response);
            }
        } catch (ClassNotFoundException | SQLException | IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(e);
        }

    }
}
