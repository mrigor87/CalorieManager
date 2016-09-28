package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;
import static ru.javawebinar.topjava.MealTestData.*;

/**
 * Created by Igor on 28.09.2016.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class MealServiceTest {
    @Autowired
    MealService service;

    @Autowired
    DbPopulator populator;

    @Before
    public void init(){
        populator.execute();
    }
    @Test
    public void get() throws Exception {
        MATCHER.assertEquals(service.get(MEAL1.getId(), UserTestData.USER_ID),MEAL1);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        service.get(MEAL1.getId(), UserTestData.ADMIN_ID);
      // MATCHER.assertEquals(repository.get(MEAL1.getId(), UserTestData.ADMIN_ID),MEAL1);
    }


    @Test
    public void delete() throws Exception {
        List<Meal> meals=new ArrayList<>(USER_MEALS);
        meals.remove(MEAL2);
        service.delete(MEAL2.getId(), UserTestData.USER_ID);
        Collection<Meal> all = service.getAll(UserTestData.USER_ID);
        MATCHER.assertCollectionEquals(all,meals);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound(){
        service.delete(MEAL8.getId(),UserTestData.USER_ID);
    }

    @Test
    public void getBetweenDates() throws Exception {
        MATCHER.assertCollectionEquals(
        service.getBetweenDates(LocalDate.of(2015,5,31),LocalDate.of(2015,5,31),UserTestData.USER_ID),
                Arrays.asList(MEAL6,MEAL5,MEAL4)
        );
    }

    @Test
    public void getBetweenDateTimes() throws Exception {
        MATCHER.assertCollectionEquals(
                service.getBetweenDateTimes(LocalDateTime.of(2015,5,31,12,0),
                                            LocalDateTime.of(2015,5,31,14,00),
                                            UserTestData.USER_ID),
                Arrays.asList(MEAL5)
        );

    }

    @Test
    public void getAll() throws Exception {
        MATCHER.assertCollectionEquals(service.getAll(UserTestData.USER_ID),USER_MEALS);
    }



    @Test
    public void update() throws Exception {
        Meal m =new Meal(MEAL3.getId(),MEAL3.getDateTime(),MEAL3.getDescription(),MEAL3.getCalories());
        m.setCalories(123);
        m.setDescription("изменил");
        m.setDateTime(LocalDateTime.of(2016,01,01,12,00));
        service.save(m,UserTestData.USER_ID);
        MATCHER.assertCollectionEquals(service.getAll(UserTestData.USER_ID),
                Arrays.asList(m,MEAL6,MEAL5,MEAL4,MEAL2,MEAL1));
    }
    @Test public  void create() throws Exception{
        Meal m= MealTestData.NEW_MEAL;
    service.save(m,UserTestData.ADMIN_ID);
        MATCHER.assertCollectionEquals(service.getAll(UserTestData.ADMIN_ID),
                Arrays.asList(MealTestData.NEW_MEAL,MEAL8,MEAL7));
    }
    @Test(expected = NotFoundException.class)
    public void updateNotFound() throws Exception {
        service.update(MEAL1,UserTestData.ADMIN_ID);
    }

}