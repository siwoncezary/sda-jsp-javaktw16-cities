package controller;

import dao.CityDao;
import repository.CityFileRepository;
import entity.CityBean;

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
import java.util.stream.Stream;

@WebServlet(urlPatterns = {"/city/one", "/city/all", "/city/find", "/city/start_find"})
public class CitiesServlet extends HttpServlet {
    private final static int PAGE_SIZE = 20;

    CityDao repository;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        InputStream stream = context.getResourceAsStream("/WEB-INF/cities500.txt");
        repository = new CityFileRepository(stream);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getRequestURI();
        if (path.endsWith("one")){
            int id = Integer.valueOf(req.getParameter("id"));
            req.setAttribute("city", repository.select(id));
            req.getRequestDispatcher("/city/city_details.jsp").forward(req,resp);
            return;
        }

        if (path.endsWith("all")){
            int currentPage = parseIntWithDefaultValue(req.getParameter("page"));
            int lastPage = (int) (repository.findAll().count()/PAGE_SIZE);
            currentPage = Integer.min(currentPage, lastPage);
            req.setAttribute("current", currentPage);
            req.setAttribute("last", lastPage);
            req.setAttribute("cities", getPage(repository.findAll(), currentPage, PAGE_SIZE));
            req.getRequestDispatcher("/city/cities.jsp").forward(req, resp);
            return;
        }
        if (path.endsWith("find")){
            String page = req.getParameter("page");
            if (page == null) {
                req.setAttribute("codes", repository.findCountryCodes());
                req.getRequestDispatcher("/city/city_form.jsp").forward(req, resp);
                return;
            }
            String value = req.getParameter("countryCode");
            int currentPage = parseIntWithDefaultValue(req.getParameter("page"));
            List<CityBean> founded = getPage(getBy("countryCode", value).stream(),currentPage, PAGE_SIZE);
            int lastPage = (int) (founded.size()/PAGE_SIZE);
            currentPage = Integer.min(currentPage, lastPage);
            req.setAttribute("current", currentPage);
            req.setAttribute("last", lastPage);
            req.setAttribute("cities", founded);
            req.getRequestDispatcher("/city/cities.jsp").forward(req, resp);
        }
    }

    private List<CityBean> getAll(){
        return repository.findAll().collect(Collectors.toList());
    }

    private List<CityBean> getBy(String field, String value){
        if (field == null || value == null){
            return Collections.emptyList();
        }
        switch(field){
            case "countryCode":
                return repository.findByCountryCode(value).collect(Collectors.toList());
            default:
                return Collections.emptyList();
        }
    }

    private List<CityBean> getPage(Stream<CityBean> stream, int page, int pageSize){
        return stream.skip(page*pageSize).limit(pageSize).collect(Collectors.toList());
    }

    private int parseIntWithDefaultValue(String str){
        if (str == null){
            return 0;
        }
        try{
            return Integer.valueOf(str);
        } catch (NumberFormatException e){
            return 0;
        }
    }
}
