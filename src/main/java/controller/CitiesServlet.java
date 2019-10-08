package controller;

import com.sun.istack.Nullable;
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
import java.util.Optional;
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
        if (path.endsWith("one")) {
            parseLong(req.getParameter("id"))
                    .ifPresent(id -> {
                        long last = repository.findAll().count()-1;
                        req.setAttribute("city", repository.select(Long.min(id, last)));
                        addNavigationAttributes(req,id, last,"id=");
                    });
            req.getRequestDispatcher("/city/city_details.jsp").forward(req, resp);
        }
        if (path.endsWith("all")) {
            long currentPage = parseLongWithDefaultValue(req.getParameter("page"));
            long lastPage = (int) (repository.findAll().count() / PAGE_SIZE);
            currentPage = Long.min(currentPage, lastPage);
            addNavigationAttributes(req, currentPage, lastPage, "page=");
            req.setAttribute("cities", getPage(repository.findAll(), currentPage, PAGE_SIZE));
            req.getRequestDispatcher("/city/cities.jsp").forward(req, resp);
            return;
        }
        if (path.endsWith("find")) {
            String page = req.getParameter("page");
            if (page == null) {
                req.setAttribute("codes", repository.findCountryCodes());
                req.getRequestDispatcher("/city/city_form.jsp").forward(req, resp);
                return;
            }
            long currentPage = Long.valueOf(req.getParameter("page"));
            String value = req.getParameter("countryCode");
            List<CityBean> founded = getPage(getBy("countryCode", value).stream(), currentPage, PAGE_SIZE);
            long lastPage = (int) (getBy("countryCode", value).size() / PAGE_SIZE);
            currentPage = Long.min(currentPage, lastPage);
            addNavigationAttributes(req, currentPage, lastPage, "page=");
            req.setAttribute("cities", founded);
            req.getRequestDispatcher("/city/cities.jsp").forward(req, resp);
        }
    }

    private List<CityBean> getAll() {
        return repository.findAll().collect(Collectors.toList());
    }

    private List<CityBean> getBy(String field, String value) {
        if (field == null || value == null) {
            return Collections.emptyList();
        }
        switch (field) {
            case "countryCode":
                return repository.findByCountryCode(value).collect(Collectors.toList());
            default:
                return Collections.emptyList();
        }
    }

    private List<CityBean> getPage(Stream<CityBean> stream, long page, long pageSize) {
        return stream.skip(page * pageSize).limit(pageSize).collect(Collectors.toList());
    }

    private Optional<Long> parseLong(@Nullable String str) {
        try {
            return Optional.ofNullable(Long.valueOf(str));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    private long parseLongWithDefaultValue(@Nullable String str){
        try {
            return str != null ? Long.valueOf(str) : 0;
        } catch (NumberFormatException e){
            return 0;
        }
    }

    private void addNavigationAttributes(HttpServletRequest req, long currentPage, long lastPage, String paramName) {
        req.setAttribute("current", paramName + currentPage);
        req.setAttribute("last", paramName + lastPage);
        req.setAttribute("prev", paramName + ((currentPage - 1) >= 0 ? (currentPage - 1) : 0));
        req.setAttribute("next", paramName + ((currentPage + 1) <= lastPage ? (currentPage + 1) : lastPage));
    }
}
