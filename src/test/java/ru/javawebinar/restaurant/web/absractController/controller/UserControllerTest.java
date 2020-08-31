package ru.javawebinar.restaurant.web.absractController.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.restaurant.Utils.exception.NotFoundException;
import ru.javawebinar.restaurant.model.Role;
import ru.javawebinar.restaurant.model.User;
import ru.javawebinar.restaurant.repository.UserRepository;

import java.util.List;

import static org.junit.Assert.assertThrows;
import static ru.javawebinar.restaurant.Utils.ValidationUtil.checkNotFoundWithId;
import static ru.javawebinar.restaurant.web.absractController.testData.UserTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:DB/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class UserControllerTest {
    @Autowired
    protected UserRepository userRepository;

    @Test
    public void create() throws Exception {
        User created = userRepository.save(getNew());
        int newId = created.id();
        User newUser = getNew();
        newUser.setId(newId);
        USER_MATCHER.assertMatch(created, newUser);
        USER_MATCHER.assertMatch(userRepository.get(newId), newUser);
    }

    @Test
    public void duplicateMailCreate() throws Exception {
        assertThrows(DataAccessException.class, () ->
                userRepository.save(new User(null, "Duplicate", "user@yandex.ru", "newPass", Role.USER)));
    }

    @Test
    public void get() throws Exception {
        User user = userRepository.get(ADMIN_ID);
        USER_MATCHER.assertMatch(user, ADMIN);
    }


    @Test
    public void getAll() throws Exception {
        List<User> all = userRepository.getAll();
        USER_MATCHER.assertMatch(all, ADMIN, USER);
    }

    @Test
    public void getNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> checkNotFoundWithId(userRepository.get(NOT_FOUND), NOT_FOUND));
    }

    @Test
    public void getByEmail() throws Exception {
        User user = userRepository.getByEmail("admin@gmail.com");
        USER_MATCHER.assertMatch(user, ADMIN);
    }

    @Test
    public void update() throws Exception {
        User updated = getUpdated();
        userRepository.save(updated);
        USER_MATCHER.assertMatch(userRepository.get(USER_ID), getUpdated());
    }


    @Test
    public void delete() throws Exception {
        userRepository.delete(USER_ID);
        assertThrows(NotFoundException.class, () -> checkNotFoundWithId(userRepository.get(USER_ID), USER_ID));
    }

    @Test
    public void deletedNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> checkNotFoundWithId(userRepository.delete(NOT_FOUND), NOT_FOUND));
    }


}
