package ru.javawebinar.topjava.web.meal;


import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javawebinar.topjava.MealTestData.*;


import org.springframework.http.MediaType;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.web.AbstractControllerTest;

/**
 * Created by Igor on 26.10.2016.
 */
public class MealRestControllerTest extends AbstractControllerTest {


    private static final String REST_URL = MealRestController.REST_URL + '/';
    @Test
    public void getAll() throws Exception {

    }

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get("/rest/meals"))
        ;

    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
// https://jira.spring.io/browse/SPR-14472
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        //.andExpect(MATCHER.contentMatcher(ADMIN))
        ;

    }

    @Test
    public void delete() throws Exception {

    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void createWithLocation() throws Exception {

    }

    @Test
    public void getBetween() throws Exception {

    }

}