package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@WebServlet(urlPatterns = "/numberError")
public class NumberError extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");
        try (PrintWriter writer = resp.getWriter()) {
            writer.write("<html><head><title>Nie z nami te numery!!!</title></head><body>");
            writer.write("<h2>Error description</h2>");
            writer.write("<ul>");
            Arrays.asList(
                    RequestDispatcher.ERROR_STATUS_CODE,
                    RequestDispatcher.ERROR_EXCEPTION_TYPE,
                    RequestDispatcher.ERROR_MESSAGE)
                    .forEach(e -> writer.write("<li>" + e + " :" + req.getAttribute(e) + " </li>"));
            writer.write("</ul>");
            writer.write("</html></body>");
        }

    }
}
