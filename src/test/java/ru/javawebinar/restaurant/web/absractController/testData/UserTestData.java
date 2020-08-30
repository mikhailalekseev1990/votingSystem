package ru.javawebinar.restaurant.web.absractController.testData;

import ru.javawebinar.restaurant.model.Role;
import ru.javawebinar.restaurant.model.User;
import ru.javawebinar.restaurant.web.absractController.TestMatcher;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

public class UserTestData {
    public static TestMatcher<User> USER_MATCHER = TestMatcher.usingFieldsWithIgnoringAssertions(User.class,"registered", "password");

    public static final int NOT_FOUND = 10;
    public static final int USER_ID = 100_000;
    public static final int ADMIN_ID = 100_001;

    public static final User USER = new User(USER_ID, "User", "user@yandex.ru", "password", Role.USER);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", Role.ADMIN);


    public static User getNew() {
        return new User(null, "New", "new@gmail.com", "newPass", new Date(), true, LocalDateTime.now(), 0, Collections.singleton(Role.USER));
    }

    public static User getUpdated() {
        User updated = new User(USER);
        updated.setName("UpdatedName");
        return updated;
    }
}
