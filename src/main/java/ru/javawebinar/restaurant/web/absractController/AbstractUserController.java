package ru.javawebinar.restaurant.web.absractController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.javawebinar.restaurant.model.User;
import ru.javawebinar.restaurant.service.UserService;
import ru.javawebinar.restaurant.web.security.AuthorizedUser;

import java.util.List;

import static ru.javawebinar.restaurant.Utils.ValidationUtil.*;

public abstract class AbstractUserController{
    protected final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    public List<User> getAll() {
        LOG.info("getAll");
        return userService.getAll();
    }

    public User get(int id) {
        LOG.info("get {}", id);
        return userService.get(id);
    }

    public User create(User user) {
        LOG.info("create {}", user);
        return userService.create(user);
    }

    public void update(User user, int id) {
        LOG.info("update {} with id={}", user, id);
        userService.update(user, id);
    }

    public void delete(int id) {
        LOG.info("delete {}", id);
        userService.delete(id);
    }

    public User getByMail(String email) {
        LOG.info("getByEmail {}", email);
        return userService.getByEmail(email);
    }

    public User getWithRestaurants(int id) {
        LOG.info("getWithMeals {}", id);
        return checkNotFoundWithId(userService.getWithRestaurants(id), id);
    }

}

