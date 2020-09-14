package ru.javawebinar.restaurant.web.absractController.testData;

import ru.javawebinar.restaurant.model.Restaurant;
import ru.javawebinar.restaurant.model.Role;
import ru.javawebinar.restaurant.model.User;
import ru.javawebinar.restaurant.web.absractController.TestMatcher;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTestData {
    public static TestMatcher<User> USER_MATCHER = TestMatcher.usingFieldsWithIgnoringAssertions(User.class, "registration", "voteTime", "restaurants", "password");
    public static TestMatcher<User> USER_WITH_RESTAURANTS_MATCHER =
            TestMatcher.usingAssertions(User.class,
                    (a, e) -> assertThat(a).usingRecursiveComparison(),
                    (a, e) -> {
                        throw new UnsupportedOperationException();
                    });
    public static final int NOT_FOUND = 10;
    public static final int ADMIN_ID = 100_000;
    public static final int USER_ID = 100_001;
    public static final int RESTAURANT_ADMIN_ID = 100_002;

    public static final User USER = new User(USER_ID, "User", "user@yandex.ru", "{noop}user", Role.USER);
    public static final User RESTAURANT_ADMIN_1 = new User(RESTAURANT_ADMIN_ID, "RESTAURANT1_ADMIN", "admin@restaurant1.ru", "{noop}restaurant1", Role.RESTAURANT_ADMIN);
    public static final User RESTAURANT_ADMIN_2 = new User(RESTAURANT_ADMIN_ID+1, "RESTAURANT2_ADMIN", "admin@restaurant2.ru", "{noop}restaurant2", Role.RESTAURANT_ADMIN);
    public static final User RESTAURANT_ADMIN_3 = new User(RESTAURANT_ADMIN_ID+2, "RESTAURANT3_ADMIN", "admin@restaurant3.ru", "{noop}restaurant3", Role.RESTAURANT_ADMIN);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "{noop}admin", Role.ADMIN);

    public static final List<User> USERS = List.of(ADMIN, RESTAURANT_ADMIN_1, RESTAURANT_ADMIN_2, RESTAURANT_ADMIN_3, USER );


    public static User getNew() {
        return new User(null, "New", "new@gmail.com", "newPass", true, new Date(), true, LocalDateTime.now(), 0, Collections.singleton(Role.USER));
    }

    public static User getUpdated() {
        User updated = new User(USER);
        updated.setName("UpdatedName");
        return updated;
    }
}
