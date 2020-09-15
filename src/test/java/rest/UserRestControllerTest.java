package rest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javawebinar.restaurant.Utils.exception.NotFoundException;
import ru.javawebinar.restaurant.Utils.json.JsonUtil;
import ru.javawebinar.restaurant.model.User;
import ru.javawebinar.restaurant.repository.UserRepository;
import ru.javawebinar.restaurant.web.absractController.AbstractControllerTest;
import ru.javawebinar.restaurant.web.absractController.TestUtil;
import testData.RestaurantTestData;
import testData.UserTestData;
import ru.javawebinar.restaurant.web.rest.UserRestController;

import static org.junit.Assert.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javawebinar.restaurant.Utils.ValidationUtil.checkNotFoundWithId;
import static ru.javawebinar.restaurant.web.absractController.TestUtil.userHttpBasic;
import static testData.DishTestData.DISH_ID_2;
import static testData.UserTestData.*;

public class UserRestControllerTest extends AbstractControllerTest {
    public static String REST_URL = UserRestController.REST_URL + "/";

    @Autowired
    UserRepository userRepository;

    @Test
    public void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(USER_MATCHER.contentJson(USERS));
    }

    @Test
    public void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + USER_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(USER_MATCHER.contentJson(USER));
    }

    @Test
    public void createWithLocation() throws Exception {
        User newUser = UserTestData.getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newUser))
                .with(userHttpBasic(ADMIN)));
        User created = TestUtil.readFromJson(action, User.class);
        int newId = created.id();
        newUser.setId(newId);
        USER_MATCHER.assertMatch(created, newUser);
        USER_MATCHER.assertMatch(userRepository.get(newId), newUser);
    }

    @Test
    public void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + USER_ID)
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> checkNotFoundWithId(userRepository.get(USER_ID), ADMIN_ID));

    }

    @Test
    public void update() throws Exception {
        User updated = UserTestData.getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL + USER_ID).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isNoContent());
        USER_MATCHER.assertMatch(userRepository.get(RestaurantTestData.USER_ID), updated);

    }

    @Test
    public void getByMail() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + USER_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(USER_MATCHER.contentJson(USER));
    }

    @Test
    public void getWithRestaurants() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + RESTAURANT_ADMIN_ID + "/with-restaurants")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(USER_WITH_RESTAURANTS_MATCHER.contentJson(RESTAURANT_ADMIN_1));
    }

    @Test
    public void getUnauth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + DISH_ID_2))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void getForbidden() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL)
                .with(userHttpBasic(USER)))
                .andExpect(status().isForbidden());
    }
}