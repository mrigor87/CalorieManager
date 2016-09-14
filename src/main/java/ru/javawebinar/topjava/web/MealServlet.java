package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.memory.MealRepositoryImplMemory;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Игорь on 13.09.2016.
 */
public class MealServlet extends HttpServlet {
    MealRepository repository=new MealRepositoryImplMemory();
    private static final Logger LOG = getLogger(MealServlet.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action=request.getParameter("action");
        if (action==null) {
            LOG.info("forward to mealList get all");
            List<MealWithExceed> meals = MealsUtil.getFilteredWithExceeded(MealsUtil.meals, LocalTime.MIN, LocalTime.MAX, 2000);
            request.setAttribute("meals", meals);
            request.getRequestDispatcher("/mealList.jsp").forward(request, response);
        }else if(action.equals("delete")){
            repository.delete(Integer.parseInt( request.getParameter("id")));
        }
    }
}
