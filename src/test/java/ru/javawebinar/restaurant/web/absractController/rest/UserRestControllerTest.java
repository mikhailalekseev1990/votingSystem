package ru.javawebinar.restaurant.web.absractController.rest;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javawebinar.restaurant.web.absractController.AbstractControllerTest;
import ru.javawebinar.restaurant.web.rest.RestaurantRestController;
import ru.javawebinar.restaurant.web.rest.UserRestController;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javawebinar.restaurant.web.absractController.testData.RestaurantTestData.RESTAURANT_1;
import static ru.javawebinar.restaurant.web.absractController.testData.RestaurantTestData.RESTAURANT_MATCHER;
import static ru.javawebinar.restaurant.web.absractController.testData.UserTestData.*;

public class UserRestControllerTest extends AbstractControllerTest {
    public static String REST_URL= UserRestController.REST_URL+'/';

    @Test
    public void getAll() {
    }

    @Test
    public void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL+USER_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(USER_MATCHER.contentJson(USER));
    }

    @Test
    public void createWithLocation() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void update() {
    }

    @Test
    public void getByMail() {
    }

    @Test
    public void getWithRestaurants() {
    }
}