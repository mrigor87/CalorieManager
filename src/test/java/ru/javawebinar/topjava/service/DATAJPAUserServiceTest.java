package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import static ru.javawebinar.topjava.UserTestData.MATCHER;
import static ru.javawebinar.topjava.UserTestData.USER;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

/**
 * Created by Igor on 13.10.2016.
 */
@ActiveProfiles({Profiles.DATAJPA,Profiles.ACTIVE_DB})
public class DATAJPAUserServiceTest extends AbstractUserServiceTest {
    @Test
    public void getWithMeals() throws Exception {
        User user = service.getWithMeals(USER_ID);
        MATCHER.assertEquals(USER, user);
        MealTestData.MATCHER.assertCollectionEquals(user.getMeals(),MealTestData.MEALS);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        service.getWithMeals(1);
    }

}
