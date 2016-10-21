package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.TimeUtil;
import ru.javawebinar.topjava.web.meal.MealRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * User: gkislin
 * Date: 19.08.2014
 */
@RequestMapping(value = "/meals")
@Controller
public class MealServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);
    @Autowired
    private MealRestController mealController;

    @Override
    public void init(ServletConfig config) throws ServletException {
/*        super.init(config);
        WebApplicationContext springContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        mealController = springContext.getBean(MealRestController.class);*/
    }



    @RequestMapping(method = RequestMethod.GET)
    public String getAll(Model model){
      //  List<MealWithExceed> all = mealController.getAll();
        model.addAttribute("meals",mealController.getAll());
        return "/meals";
    }
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(name = "id") Integer id, Model model){
        mealController.delete(id);
        model.addAttribute("meals",mealController.getAll());
        return "redirect:/meals";
    }

    @RequestMapping(value = "/update",method = RequestMethod.GET)
    public String update(@RequestParam(name = "id") Integer id, Model model){
        Meal meal = mealController.get(id);
        model.addAttribute("meal",meal);
        return "meal";
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            final Meal meal = new Meal(
                    LocalDateTime.parse(request.getParameter("dateTime")),
                    request.getParameter("description"),
                    Integer.valueOf(request.getParameter("calories")));

            if (request.getParameter("id").isEmpty()) {
                LOG.info("Create {}", meal);
                mealController.create(meal);
            } else {
                LOG.info("Update {}", meal);
                mealController.update(meal, getId(request));
            }
            response.sendRedirect("meals");

        } else if ("filter".equals(action)) {
            LocalDate startDate = TimeUtil.parseLocalDate(resetParam("startDate", request));
            LocalDate endDate = TimeUtil.parseLocalDate(resetParam("endDate", request));
            LocalTime startTime = TimeUtil.parseLocalTime(resetParam("startTime", request));
            LocalTime endTime = TimeUtil.parseLocalTime(resetParam("endTime", request));
            request.setAttribute("meals", mealController.getBetween(startDate, startTime, endDate, endTime));
            request.getRequestDispatcher("/meals.jsp").forward(request, response);
        }
    }

    private String resetParam(String param, HttpServletRequest request) {
        String value = request.getParameter(param);
        request.setAttribute(param, value);
        return value;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
/*            LOG.info("getAll");
            request.setAttribute("meals", mealController.getAll());
            request.getRequestDispatcher("/meals.jsp").forward(request, response);*/

        } else if ("delete".equals(action)) {
            int id = getId(request);
            LOG.info("Delete {}", id);
            mealController.delete(id);
            response.sendRedirect("meals");

        } else if ("create".equals(action) || "update".equals(action)) {
            final Meal meal = "create".equals(action) ?
                    new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), "", 1000) :
                    mealController.get(getId(request));
            request.setAttribute("meal", meal);
            request.getRequestDispatcher("meal.jsp").forward(request, response);
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}
