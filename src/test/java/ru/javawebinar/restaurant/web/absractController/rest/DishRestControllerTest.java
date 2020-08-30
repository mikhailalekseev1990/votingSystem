package ru.javawebinar.restaurant.web.absractController.rest;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javawebinar.restaurant.web.absractController.AbstractControllerTest;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javawebinar.restaurant.web.absractController.testData.DishTestData.*;


public class DishRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = "/rest/restaurant/100002/dishes/";

    @Test
    public void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL  + DISH_ID_1))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_MATCHER.contentJson(DISH_1));
    }

    @Test
    public void delete() {
    }

    @Test
    public void getAll() {
    }

    @Test
    public void update() {
    }

    @Test
    public void createWithLocation() {
    }
}