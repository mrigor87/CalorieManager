package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Collection;

/**
 * GKislin
 * 15.06.2015.
 */
public interface MealService {
    Meal save(Meal meal, int userId) throws NotFoundException;
    boolean delete (int id, int userId) throws NotFoundException;
    Meal get(int id, int userId) throws NotFoundException;
    Collection<MealWithExceed>getAll(int userId);
   // Collection<MealWithExceed>getBetweenAll(int userId);

}
