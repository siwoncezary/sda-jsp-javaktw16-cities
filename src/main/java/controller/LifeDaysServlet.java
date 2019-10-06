package controller;

import entity.UserBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/life")
public class LifeDaysServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("life/life_form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dateStr= req.getParameter("birthDate");
        UserBean user = new UserBean();
        user.setBirthDate(dateStr);
        long lifeDays = user.getLifeDays();
        req.setAttribute("lifeDays", lifeDays);
        req.getRequestDispatcher("life/life_days.jsp").forward(req, resp);
    }
}
