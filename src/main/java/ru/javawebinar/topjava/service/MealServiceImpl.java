package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Collection;

/**
 * GKislin
 * 06.03.2015.
 */
@Service
public class MealServiceImpl implements MealService {
    @Autowired
    private MealRepository repository;

    @Override
    public Meal save(Meal meal, int userId) throws NotFoundException {
        return repository.save(meal,userId);
    }

    @Override
    public boolean delete(int id, int userId) throws NotFoundException {
        return repository.delete(id,userId);
    }

    @Override
    public Meal get(int id, int userId) throws NotFoundException {
        return repository.get(id,userId);
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        return repository.getAll(userId);
    }
}
