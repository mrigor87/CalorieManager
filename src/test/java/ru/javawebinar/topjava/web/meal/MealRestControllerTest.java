package ru.javawebinar.topjava.web.meal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.repository.MealRepository;

import static org.junit.Assert.*;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.USER_ID;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;

/**
 * Created by Igor on 28.09.2016.
 */
@ContextConfiguration("classpath:spring/spring-app-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class MealRestControllerTest {
    @Autowired
    MealRepository repository;

    @Autowired
    MealRestController controller;

    @Before
    public void init(){
        repository.save(MEAL1,USER_ID);
        repository.save(MEAL2,USER_ID);
        repository.save(MEAL3,USER_ID);
        repository.save(MEAL4,USER_ID);
        repository.save(MEAL5,USER_ID);
        repository.save(MEAL6,USER_ID);
        repository.save(MEAL7,ADMIN_ID);
        repository.save(MEAL8,ADMIN_ID);
    }
    @Test
    public void get() throws Exception {

    }

    @Test
    public void delete() throws Exception {

    }

    @Test
    public void getAll() throws Exception {

    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void create() throws Exception {

    }

    @Test
    public void getBetween() throws Exception {

    }

}