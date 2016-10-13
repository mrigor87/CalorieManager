package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.List;

/**
 * GKislin
 * 27.03.2015.
 */

@Repository
public class DataJpaUserRepositoryImpl implements UserRepository {

    @Autowired
    private CrudUserRepository crudRepository;
    @Autowired
    private CrudMealRepository MealCrudRepository;

    @Override
    public User save(User user) {
        return crudRepository.save(user);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public User get(int id) {
        User user=crudRepository.findOne(id);
        return user;
    }

    @Override
    public User getByEmail(String email) {
        return crudRepository.getByEmail(email);
    }

    @Override
    public List<User> getAll() {
        return crudRepository.findAll();
    }

    @Override
    @Transactional
    public User getWithMeals(int id) {
        User user=crudRepository.findOne(id);
        List<Meal> meals = user.getMeals();
        //user.setMeals();
        //User withUser = crudRepository.getWithUser(id);
        return user;
    }
}
