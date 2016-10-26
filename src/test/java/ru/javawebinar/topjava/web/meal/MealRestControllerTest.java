package ru.javawebinar.topjava.web.meal;


import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.USER_ID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.AbstractControllerTest;
import ru.javawebinar.topjava.web.json.JsonUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Igor on 26.10.2016.
 */
public class MealRestControllerTest extends AbstractControllerTest {
    @Autowired
    MealService mealService;

    private static final String REST_URL = MealRestController.REST_URL + '/';


    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + MEAL2.getId()))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(MEAL2));
        ;

    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
// https://jira.spring.io/browse/SPR-14472
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER_WITH_EXCEED.contentListMatcher(
                        MealsUtil.createWithExceed(MEAL6, false),
                        MealsUtil.createWithExceed(MEAL5, false),
                        MealsUtil.createWithExceed(MEAL4, false),
                        MealsUtil.createWithExceed(MEAL3, false),
                        MealsUtil.createWithExceed(MEAL2, false),
                        MealsUtil.createWithExceed(MEAL1, false)
                ));
        //.andExpect(MATCHER.contentMatcher(ADMIN))
        ;

    }


    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + MEAL2.getId()))
                .andDo(print())
                .andExpect(status().isOk())
        //   .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        ;
        MATCHER.assertCollectionEquals(mealService.getAll(UserTestData.USER_ID), Arrays.asList(MEAL6, MEAL5, MEAL4, MEAL3, MEAL1));
    }


    @Test
    public void update() throws Exception {
        Meal meal = MealTestData.getUpdated();
        mockMvc.perform(put(REST_URL + MEAL1.getId())
                // .andDo(print())

                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(meal)))
                .andDo(print())

                //.conte`nt
                .andExpect(status().isOk())
        ;
        MATCHER.assertCollectionEquals(mealService.getAll(UserTestData.USER_ID), Arrays.asList(MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, meal));
    }




    @Test
    public void testCreateWithLocation() throws Exception {
        Meal expected = MealTestData.getCreated();
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(expected))).andExpect(status().isCreated()).andDo(print());
        Meal returned = MATCHER.fromJsonAction(action);
        expected.setId(returned.getId());
        MATCHER.assertEquals(expected, returned);

    }

/*    MATCHER.assertCollectionEquals(Arrays.asList(MEAL3, MEAL2, MEAL1),
            service.getBetweenDates(LocalDate.of(2015, Month.MAY, 30),
                                    LocalDate.of(2015, Month.MAY, 30), USER_ID));*/

/*    mockMvc.perform(get(REST_URL + "by?email=" + USER.getEmail()))*/
    @Test
    public void testGetBetween() throws Exception {
        LocalDate startDate=LocalDate.of(2015, Month.MAY, 30);
        LocalDate endDate=LocalDate.of(2015, Month.MAY, 30);
        LocalTime startTime=LocalTime.MIN;
        LocalTime endTime=LocalTime.MAX;
        String newURL=REST_URL+"filter?" +
                "startDate="+startDate.toString()+
                "&startTime="+startTime+
                "&endDate="+endDate+
                "&endTime="+endTime;

        mockMvc.perform(get(newURL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER_WITH_EXCEED.contentListMatcher(
                        MealsUtil.createWithExceed(MEAL3, false),
                        MealsUtil.createWithExceed(MEAL2, false),
                        MealsUtil.createWithExceed(MEAL1, false)
                ));


        ;

    }

}