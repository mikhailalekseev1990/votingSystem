package ru.javawebinar.restaurant.web.absractController.rest;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javawebinar.restaurant.Utils.exception.NotFoundException;
import ru.javawebinar.restaurant.Utils.json.JsonUtil;
import ru.javawebinar.restaurant.model.Restaurant;
import ru.javawebinar.restaurant.repository.RestaurantRepository;
import ru.javawebinar.restaurant.web.absractController.AbstractControllerTest;
import ru.javawebinar.restaurant.web.absractController.TestUtil;
import ru.javawebinar.restaurant.web.absractController.testData.RestaurantTestData;
import ru.javawebinar.restaurant.web.rest.RestaurantRestController;

import static org.junit.Assert.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javawebinar.restaurant.Utils.ValidationUtil.checkNotFoundWithId;
import static ru.javawebinar.restaurant.web.absractController.TestUtil.userHttpBasic;
import static ru.javawebinar.restaurant.web.absractController.testData.DishTestData.DISH_ID_2;
import static ru.javawebinar.restaurant.web.absractController.testData.DishTestData.DISH_ID_3;
import static ru.javawebinar.restaurant.web.absractController.testData.RestaurantTestData.*;
import static ru.javawebinar.restaurant.web.absractController.testData.RestaurantTestData.USER_ID;
import static ru.javawebinar.restaurant.web.absractController.testData.UserTestData.*;

public class RestaurantRestControllerTest extends AbstractControllerTest {
    public static String REST_URL= RestaurantRestController.REST_URL+'/';

    @Autowired
    RestaurantRepository restaurantRepository;
    @Test
    public void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + RESTAURANT_ID_1)
                .with(userHttpBasic(RESTAURANT_ADMIN_1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER.contentJson(RESTAURANT_1));
    }

    @Test
    public void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + RESTAURANT_ID_3)
                .with(userHttpBasic(RESTAURANT_ADMIN_2)))
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> checkNotFoundWithId(restaurantRepository.get(RESTAURANT_ID_3, RESTAURANT2_ADMIN_ID), RESTAURANT_ID_3));
    }

    @Test
    public void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER.contentJson(RESTAURANTS));
    }

    @Test
    public void update() throws Exception {
        Restaurant updated = RestaurantTestData.getUpdate();
        perform(MockMvcRequestBuilders.put(REST_URL + RESTAURANT_ID_2).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
                .with(userHttpBasic(RESTAURANT_ADMIN_1)))
                .andExpect(status().isNoContent());
        RESTAURANT_MATCHER.assertMatch(restaurantRepository.get(RESTAURANT_ID_2, RESTAURANT1_ADMIN_ID), updated);
    }

    @Test
    public void createWithLocation() throws Exception {
        Restaurant newRestaurant = RestaurantTestData.getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newRestaurant))
                .with(userHttpBasic(RESTAURANT_ADMIN_2)));
        Restaurant created = TestUtil.readFromJson(action, Restaurant.class);
        int newId = created.id();
        newRestaurant.setId(newId);
        RESTAURANT_MATCHER.assertMatch(created, newRestaurant);
        RESTAURANT_MATCHER.assertMatch(restaurantRepository.get(newId, RESTAURANT2_ADMIN_ID), newRestaurant);
    }

    @Test
    public void getWithDishes() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + RESTAURANT_ID_1 + "/with-dishes")
                .with(userHttpBasic(RESTAURANT_ADMIN_1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_WITH_DISHES_MATCHER.contentJson(RESTAURANT_1));
    }

    @Test
    public void getUnauth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + DISH_ID_2))
                .andExpect(status().isUnauthorized());
    }
}