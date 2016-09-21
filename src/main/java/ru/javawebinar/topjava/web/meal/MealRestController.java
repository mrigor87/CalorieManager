package ru.javawebinar.topjava.web.meal;

import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.exception.ExceptionUtil;

import java.util.Collection;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class MealRestController {
    private MealService service;
    public Collection<Meal>getAll(){
        return service.getAll(AuthorizedUser.id());
    }
    public Meal get(int id){
        return ExceptionUtil.checkNotFoundWithId(service.get(id,AuthorizedUser.id()),id);
    }
    public void delete(int id){
        ExceptionUtil.checkNotFoundWithId(service.delete(id,AuthorizedUser.id()),id);
    }
    public Meal update(Meal meal){
        return ExceptionUtil.checkNotFound(service.save(meal,AuthorizedUser.id()),meal.toString());

    }
    public Meal create(Meal meal){
        return service.save(meal,AuthorizedUser.id());
    }

}
