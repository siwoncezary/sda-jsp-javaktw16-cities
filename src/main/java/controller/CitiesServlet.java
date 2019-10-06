package controller;

import dao.CityDaoList;
import entity.CityBean;
import repository.CityRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/city/one", "/city/all", "/city/find"})
public class CitiesServlet extends HttpServlet {
    CityDaoList dao;
    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        InputStream stream = context.getResourceAsStream("/WEB-INF/cities500.txt");
        dao = new CityDaoList(CityRepository.loadCities(stream));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getRequestURI();
        if (path.endsWith("one")){
            int id = Integer.valueOf(req.getParameter("id"));
            req.setAttribute("city", dao.select(id));
            req.getRequestDispatcher("/city/city_details.jsp").forward(req,resp);
            return;
        }
        if (path.endsWith("all")){
            req.setAttribute("cities", getAll());
            req.getRequestDispatcher("/city/cities.jsp").forward(req, resp);
            return;
        }
        if (path.endsWith("find")){
            req.setAttribute("codes", dao.findCountryCodes());
            req.getRequestDispatcher("/city/city_form.jsp").forward(req, resp);
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String value = req.getParameter("countryCode");
        req.setAttribute("cities", getBy("countryCode", value));
        req.getRequestDispatcher("/city/cities.jsp").forward(req, resp);
    }

    private List<CityBean> getAll(){
        return dao.findAll().limit(100).collect(Collectors.toList());
    }

    private List<CityBean> getBy(String field, String value){
        if (field == null || value == null){
            return Collections.emptyList();
        }
        switch(field){
            case "countryCode":
                return dao.findByCountryCode(value).collect(Collectors.toList());
            default:
                return Collections.emptyList();
        }
    }
}
