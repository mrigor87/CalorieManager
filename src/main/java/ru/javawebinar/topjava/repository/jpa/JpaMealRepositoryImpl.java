package ru.javawebinar.topjava.repository.jpa;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.TimeUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;

/**
 * User: gkisline
 * Date: 26.08.2014
 */

@Repository
@Transactional(readOnly = true)
public class JpaMealRepositoryImpl implements MealRepository {
    @PersistenceContext
    private EntityManager em;




    @Override
    @Transactional
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            meal.setUser(em.getReference(User.class,userId));
            em.persist(meal);
            return meal;
        } else {
            int i = em.createNamedQuery(Meal.UPDATE)
                    .setParameter("id", meal.getId())
                    .setParameter("userId", userId)
                    .setParameter("date_time", meal.getDateTime())
                    .setParameter("calories", meal.getCalories())
                    .setParameter("description", meal.getDescription())
                    .executeUpdate();
            return
            i!=0?meal:null;
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return
                em.createNamedQuery(Meal.DELETE).setParameter(1, id).setParameter(2, userId).executeUpdate() != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        List<Meal> resultList = em.createNamedQuery(Meal.GET, Meal.class).setParameter(1, id).setParameter(2, userId).getResultList();
        return DataAccessUtils.singleResult(resultList);

    }



    @Override
    public List<Meal> getAll(int userId) {
        return
                em.createNamedQuery(Meal.GET_ALL, Meal.class).setParameter(1, userId).getResultList();
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
     //   LocalDateTime start=startDate!=null?startDate: LocalDateTime.MIN;
       // LocalDateTime end=endDate!=null?endDate: LocalDateTime.MAX;

        return
                em.createNamedQuery(Meal.GET_DETWEEN, Meal.class)
                        .setParameter("userId",userId)
                        .setParameter("endDate",endDate)
                        .setParameter("startDate", startDate)
                        .getResultList();
    }
}