package ru.javawebinar.restaurant.web.absractController.rest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javawebinar.restaurant.Utils.exception.NotFoundException;
import ru.javawebinar.restaurant.repository.UserRepository;
import ru.javawebinar.restaurant.web.absractController.AbstractControllerTest;
import ru.javawebinar.restaurant.web.absractController.testData.RestaurantTestData;
import ru.javawebinar.restaurant.web.rest.RestaurantRestController;
import ru.javawebinar.restaurant.web.rest.UserRestController;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javawebinar.restaurant.web.absractController.testData.RestaurantTestData.*;
import static ru.javawebinar.restaurant.web.absractController.testData.UserTestData.*;
import static ru.javawebinar.restaurant.web.absractController.testData.UserTestData.ADMIN;
import static ru.javawebinar.restaurant.web.absractController.testData.UserTestData.USER;

public class UserRestControllerTest extends AbstractControllerTest {
    public static String REST_URL = UserRestController.REST_URL + '/';

    @Autowired
    UserRepository userRepository;

    @Test
    public void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(USER_MATCHER.contentJson(USERS));
    }

    @Test
    public void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + USER_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(USER_MATCHER.contentJson(USER));
    }

    @Test
    public void createWithLocation() {
    }

    @Test
    public void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + USER_ID))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> userRepository.get(USER_ID));

    }

    @Test
    public void update() {
    }

    @Test
    public void getByMail() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + USER_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(USER_MATCHER.contentJson(USER));
    }

    @Test
    public void getWithRestaurants() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + ADMIN_ID + "/with-restaurants"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(USER_WITH_RESTAURANTS_MATCHER.contentJson(ADMIN));
    }
}