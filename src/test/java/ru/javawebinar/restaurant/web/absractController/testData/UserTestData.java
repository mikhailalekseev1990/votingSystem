package ru.javawebinar.restaurant.web.absractController.testData;

import ru.javawebinar.restaurant.model.Role;
import ru.javawebinar.restaurant.model.User;
import ru.javawebinar.restaurant.web.absractController.TestMatcher;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTestData {
    public static TestMatcher<User> USER_MATCHER = TestMatcher.usingFieldsWithIgnoringAssertions(User.class, "registration", "voteTime", "restaurants");
    public static TestMatcher<User> USER_WITH_RESTAURANTS_MATCHER =
            TestMatcher.usingAssertions(User.class,
                    (a, e) -> assertThat(a).usingRecursiveComparison()
                            .ignoringFields("registration", "voteTime").ignoringAllOverriddenEquals().isEqualTo(e),
                    (a, e) -> {
                        throw new UnsupportedOperationException();
                    });
    public static final int NOT_FOUND = 10;
    public static final int ADMIN_ID = 100_000;
    public static final int USER_ID = 100_001;
    public static final int RESTAURANT_ADMIN_ID = 100_002;

    public static final User USER = new User(USER_ID, "User", "user@yandex.ru", "password", Role.USER);
    public static final User RESTAURANT_ADMIN = new User(RESTAURANT_ADMIN_ID, "RESTAURANT_ADMIN", "admin@restaurant.com", "restaurant", Role.RESTAURANT_ADMIN);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@.gmailcom", "admin", Role.RESTAURANT_ADMIN);

    public static final List<User> USERS = List.of(RESTAURANT_ADMIN, USER, ADMIN);


    public static User getNew() {
        return new User(null, "New", "new@gmail.com", "newPass", true, new Date(), true, LocalDateTime.now(), 0, Collections.singleton(Role.USER));
    }

    public static User getUpdated() {
        User updated = new User(USER);
        updated.setName("UpdatedName");
        return updated;
    }
}
