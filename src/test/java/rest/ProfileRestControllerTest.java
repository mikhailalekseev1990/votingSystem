package rest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javawebinar.restaurant.Utils.UserUtil;
import ru.javawebinar.restaurant.Utils.json.JsonUtil;
import ru.javawebinar.restaurant.model.Role;
import ru.javawebinar.restaurant.model.User;
import ru.javawebinar.restaurant.repository.UserRepository;
import ru.javawebinar.restaurant.to.UserTo;
import ru.javawebinar.restaurant.web.absractController.AbstractControllerTest;
import ru.javawebinar.restaurant.web.rest.ProfileRestController;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javawebinar.restaurant.web.absractController.TestUtil.readFromJson;
import static testData.UserTestData.USER_MATCHER;

public class ProfileRestControllerTest extends AbstractControllerTest {
    public static String REST_URL = ProfileRestController.REST_URL;
    @Autowired
    UserRepository userRepository;

    @Test
    public void register() throws Exception {
        UserTo newTo = new UserTo( "New Name", "newName@ya.ru", "newPassword", Role.USER);
        User newUser = UserUtil.createNewFromTo(newTo);
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newTo)))
                .andDo(print())
                .andExpect(status().isCreated());

        User created = readFromJson(action, User.class);
        int newId = created.id();
        newUser.setId(newId);
        USER_MATCHER.assertMatch(created, newUser);
        USER_MATCHER.assertMatch(userRepository.get(newId), newUser);
    }
}