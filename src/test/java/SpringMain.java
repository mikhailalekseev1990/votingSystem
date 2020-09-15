import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.Profiles;
import ru.javawebinar.restaurant.model.Role;
import ru.javawebinar.restaurant.model.User;
import ru.javawebinar.restaurant.web.rest.DishRestController;
import ru.javawebinar.restaurant.web.rest.UserRestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static ru.javawebinar.restaurant.web.absractController.TestUtil.mockAuthorize;
import static testData.UserTestData.ADMIN;
import static testData.UserTestData.USER;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 automatic resource management
    }
}
