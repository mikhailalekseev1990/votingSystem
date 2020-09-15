package rest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javawebinar.restaurant.Utils.exception.NotFoundException;
import ru.javawebinar.restaurant.Utils.json.JsonUtil;
import ru.javawebinar.restaurant.model.Dish;
import ru.javawebinar.restaurant.repository.DishRepository;
import ru.javawebinar.restaurant.web.absractController.AbstractControllerTest;
import ru.javawebinar.restaurant.web.absractController.TestUtil;
import testData.DishTestData;

import static org.junit.Assert.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javawebinar.restaurant.Utils.ValidationUtil.checkNotFoundWithId;

import static ru.javawebinar.restaurant.web.absractController.TestUtil.userHttpBasic;
import static testData.DishTestData.*;
import static testData.RestaurantTestData.RESTAURANT_ID_1;
import static testData.UserTestData.*;


public class DishRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = "/rest/restaurants/100005/dishes/";

    @Autowired
    DishRepository dishRepository;

    @Test
    public void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + DISH_ID_3)
                .with(userHttpBasic(RESTAURANT_ADMIN_1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_MATCHER.contentJson(DISH_3));
    }

    @Test
    public void getNotOwn() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + (DISH_ID_3))
                .with(userHttpBasic(RESTAURANT_ADMIN_1)))
                .andExpect(status().isOk());
        assertThrows(NotFoundException.class, () -> checkNotFoundWithId(dishRepository.get(DISH_ID_3, RESTAURANT_ID_1 + 1), DISH_ID_3));
    }

    @Test
    public void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + DISH_ID_1)
                .with(userHttpBasic(RESTAURANT_ADMIN_1)))
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> checkNotFoundWithId(dishRepository.get(DISH_ID_1, RESTAURANT_ID_1), RESTAURANT_ID_1));
    }

    @Test
    public void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get("/rest/restaurants/100006/dishes/")
                .with(userHttpBasic(RESTAURANT_ADMIN_1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_MATCHER.contentJson(DISHES_FOR_RESTAURANT_2));
    }

    @Test
    public void update() throws Exception {
        Dish updated = DishTestData.getUpdate();
        perform(MockMvcRequestBuilders.put(REST_URL + (DISH_ID_2)).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
                .with(userHttpBasic(RESTAURANT_ADMIN_1)))
                .andExpect(status().isNoContent());
        DISH_MATCHER.assertMatch(dishRepository.get(DISH_ID_2, RESTAURANT_ID_1), updated);
    }

    @Test
    public void createWithLocation() throws Exception {
        Dish newDish = DishTestData.getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newDish))
                .with(userHttpBasic(RESTAURANT_ADMIN_1)));
        Dish created = TestUtil.readFromJson(action, Dish.class);
        int newId = created.id();
        newDish.setId(newId);
        DISH_MATCHER.assertMatch(created, newDish);
        DISH_MATCHER.assertMatch(dishRepository.get(newId, RESTAURANT_ID_1), newDish);
    }

    @Test
    public void getUnauth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + DISH_ID_2))
                .andExpect(status().isUnauthorized());
    }
}