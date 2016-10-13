package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;

/**
 * Created by Igor on 13.10.2016.
 */
@ActiveProfiles({Profiles.DATAJPA,Profiles.ACTIVE_DB})
public class DATAJPAMealServiceTest extends AbstractMealServiceTest {
    @Test
    public void getWithUser() throws Exception {
        Meal actual = service.getWithUser(ADMIN_MEAL_ID, ADMIN_ID);
        MATCHER.assertEquals(ADMIN_MEAL1, actual);
        UserTestData.MATCHER.assertEquals(actual.getUser(),UserTestData.ADMIN);
    }

    @Test
    public void testGetNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.getWithUser(MEAL1_ID, ADMIN_ID);
    }


}
