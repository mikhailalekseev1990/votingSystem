package ru.javawebinar.restaurant.web.absractController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import ru.javawebinar.restaurant.model.Restaurant;
import ru.javawebinar.restaurant.model.User;
import ru.javawebinar.restaurant.repository.UserRepository;
import ru.javawebinar.restaurant.web.SecurityUtil;

import java.util.List;

import static ru.javawebinar.restaurant.Utils.ValidationUtil.*;

public abstract class AbstractUserController {
    protected final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserRepository userRepository;

    public List<User> getAll() {
        LOG.info("getAll");
        return userRepository.getAll();
    }

    public User get(int id) {
        LOG.info("get {}", id);
        return checkNotFoundWithId(userRepository.get(id), id);
    }

    public User create(User user) {
        LOG.info("create {}", user);
        checkNew(user);
        Assert.notNull(user, "user must not be null");
        return userRepository.save(user);
    }

    public void update(User user, int id) {
        LOG.info("update {} with id={}", user, id);
        assureIdConsistent(user, id);
        Assert.notNull(user, "user must not be null");
        checkNotFoundWithId(userRepository.save(user), user.id());
    }

    public void delete(int id) {
        LOG.info("delete {}", id);
        checkNotFoundWithId(userRepository.delete(id), id);
    }

    public User getByMail(String email) {
        LOG.info("getByEmail {}", email);
        Assert.notNull(email, "email must not be null");
        return checkNotFound(userRepository.getByEmail(email), "email=" + email);
    }

    public User getWithRestaurants(int id) {
        LOG.info("getWithMeals {}", id);
        return checkNotFoundWithId(userRepository.getWithRestaurants(id), id);
    }


}

