package servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.jsp.jstl.core.Config;

import java.io.IOException;

@WebServlet(name = "LanguageServlet", value = "/LanguageServlet")
public class LanguageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String localeToSet = request.getParameter("locale");

        HttpSession session = request.getSession();
        Config.set(session, "jakarta.servlet.jsp.jstl.fmt.locale", localeToSet);

        response.sendRedirect("/web_jdbc_war_exploded");
    }
}
