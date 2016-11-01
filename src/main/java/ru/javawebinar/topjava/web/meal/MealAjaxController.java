package ru.javawebinar.topjava.web.meal;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.security.access.method.P;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by Игорь on 30.10.2016.
 */
@RequestMapping(value = MealAjaxController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class MealAjaxController extends AbstractMealController {
    static final String REST_URL = "/ajax/meals";

    @GetMapping
    public List<MealWithExceed> getAll(HttpServletRequest request, Model model, HttpServletResponse response) {

        return super.getAll();
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @PostMapping
    public void updateOrCreate1(@RequestParam("id") Integer id,
                                  @RequestParam("dateTime")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime,
                                  @RequestParam("description") String description,
                                  @RequestParam("calories") Integer calories) {
        Meal userMeal = new Meal(id,dateTime,description,calories);
        if (userMeal.isNew()) {
            super.create(userMeal);
        } else {
            super.update(userMeal, userMeal.getId());
        }
    }

    @RequestMapping(value = "/filter", method = RequestMethod.POST)
    public List<MealWithExceed> getBetween(
            @RequestParam(value = "startDate", required = false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(value = "startTime", required = false)@DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime startTime,
            @RequestParam(value = "endDate", required = false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(value = "endTime", required = false)@DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime endTime) {
        return super.getBetween(startDate, startTime, endDate, endTime);
    }


}
