package controller;

import dao.CityDaoList;
import entity.CityBean;
import repository.CityRepository;
import util.Paginator;

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
    static final int PAGE_SIZE = 10;

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
            String pageStr = req.getParameter("page");
            int page;
            if (pageStr == null) {
                page = 1;
            } else {
                page = Integer.valueOf(pageStr);
            }
            Paginator.ofStream(dao.findAll(), PAGE_SIZE, page).ifPresent(
                    paginator -> {
                        addPagingAttributes(req, page, paginator);
                        try {
                            req.getRequestDispatcher("/city/cities.jsp").forward(req, resp);
                        } catch (ServletException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
            );
            return;
        }

        if (path.endsWith("find")){
            String pageStr = req.getParameter("page");
            if (pageStr == null) {
                req.setAttribute("codes", dao.findCountryCodes());
                req.getRequestDispatcher("/city/city_form.jsp").forward(req, resp);
            } else {
                int page = Integer.valueOf(pageStr);
                String value = req.getParameter("countryCode");
                Paginator.ofStream(getBy("countryCode", value).stream(), PAGE_SIZE, page).ifPresent(
                        paginator -> {
                            addPagingAttributes(req, page, paginator);
                            try {
                                req.getRequestDispatcher("/city/cities.jsp").forward(req, resp);
                            } catch (ServletException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                );
            }
            return;
        }
    }

    private void addPagingAttributes(HttpServletRequest req, int page, Paginator paginator) {
        req.setAttribute("cities", paginator.getPage());

        req.setAttribute("current", "page="+page);
        paginator.next().ifPresent(p->{
            req.setAttribute("next", "page=" + p);
        });
        paginator.prev().ifPresent(p->{
            req.setAttribute("prev", "page=" + p);
        });
        req.setAttribute("last", "page="+paginator.count());
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
