package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

/**
 * Created by Игорь on 14.09.2016.
 */
public interface MealRepository {
    Meal get (int id);
    List<Meal> getAll();
    Meal save (Meal meal);
    void delete(int id);
}
