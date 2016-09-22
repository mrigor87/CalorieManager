package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.ExceptionUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Collections;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class MealRestController {
    @Autowired
    private MealService service;

    public Collection<MealWithExceed> getAll() {
        return MealsUtil.getWithExceeded(service.getAll(AuthorizedUser.id()), MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    public Meal get(int id) {
        return ExceptionUtil.checkNotFoundWithId(service.get(id, AuthorizedUser.id()), id);
    }

    public void delete(int id) {
        ExceptionUtil.checkNotFoundWithId(service.delete(id, AuthorizedUser.id()), id);
    }

    public Meal update(Meal meal) {
        return ExceptionUtil.checkNotFound(service.save(meal, AuthorizedUser.id()), meal.toString());

    }

    public Meal create(Meal meal) {
        return service.save(meal, AuthorizedUser.id());
    }

    public Collection<MealWithExceed> getBetween(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        LocalDate startDateNotNull=startDate!=null?startDate:LocalDate.MIN;
        LocalDate endDateNotNull=startDate!=null?endDate:LocalDate.MAX;
        LocalTime startTimeNotNull=startTime!=null?startTime:LocalTime.MIN;
        LocalTime endTimeNotNull=startTime!=null?endTime:LocalTime.MAX;
        service.getBetween(startDate, endDate, AuthorizedUser.id());
        return Collections.EMPTY_LIST;
    }

}
