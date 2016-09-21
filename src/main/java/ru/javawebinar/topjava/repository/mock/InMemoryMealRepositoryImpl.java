package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.UserUtil;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * GKislin
 * 15.09.2015.
 */
@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    //          userId        mealId
    private Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        // MealsUtil.MEALS.forEach(this::save);
        save(MealsUtil.MEALS.get(0), UserUtil.getUser().getId());
        save(MealsUtil.MEALS.get(1), UserUtil.getUser().getId());
        save(MealsUtil.MEALS.get(2), UserUtil.getUser().getId());
        save(MealsUtil.MEALS.get(3), UserUtil.getAdmin().getId());
        save(MealsUtil.MEALS.get(4), UserUtil.getAdmin().getId());
        save(MealsUtil.MEALS.get(5), UserUtil.getAdmin().getId());

    }

    @Override
    public Meal save(Meal meal, int userId) {
        Map<Integer, Meal> meals = repository.get(userId);
        if (!(meals == null) && meal.isNew()) {
            return null;
        }
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        }
        repository.computeIfAbsent(userId, (User) -> new ConcurrentHashMap<>()).put(meal.getId(), meal);
//        meals.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public boolean delete(int id, int userId) {
        Map<Integer, Meal> meals = repository.get(userId);
        if (!meals.isEmpty()) {
            return meals.remove(id, meals.get(id));
        } else {
            return false;
        }

    }

    @Override
    public Meal get(int id, int userId) {
        Map<Integer, Meal> meals = repository.get(userId);
        if (!meals.isEmpty()) {
            return meals.get(id);
        } else {
            return null;
        }
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        Map<Integer, Meal> meals = repository.get(userId);
        if (!meals.isEmpty()) {
            return meals.values();
        } else {
            return Collections.EMPTY_LIST;
        }

    }
}

