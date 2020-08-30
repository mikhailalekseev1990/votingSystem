package ru.javawebinar.restaurant.web.absractController.rest;


import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javawebinar.restaurant.web.absractController.AbstractControllerTest;
import ru.javawebinar.restaurant.web.rest.RestaurantRestController;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javawebinar.restaurant.web.absractController.testData.RestaurantTestData.*;

public class RestaurantRestControllerTest extends AbstractControllerTest {
    public static String REST_URL= RestaurantRestController.REST_URL+'/';
    @Test
    public void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + RESTAURANT_ID_1))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER.contentJson(RESTAURANT_1));
    }
}