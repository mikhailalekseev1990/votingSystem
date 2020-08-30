package ru.javawebinar.restaurant.web.absractController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import ru.javawebinar.restaurant.model.Restaurant;
import ru.javawebinar.restaurant.model.Role;
import ru.javawebinar.restaurant.model.User;
import ru.javawebinar.restaurant.repository.RestaurantRepository;
import ru.javawebinar.restaurant.repository.UserRepository;
import ru.javawebinar.restaurant.web.SecurityUtil;

import java.util.List;
import java.util.Set;

import static ru.javawebinar.restaurant.Utils.ValidationUtil.*;

public abstract class AbstractRestaurantController {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractRestaurantController.class);

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    UserRepository userRepository;

    public User getUser(int u_id) {
        return checkNotFoundWithId(userRepository.get(u_id), u_id);
    }

    public Restaurant get(int id) {
        int u_id = SecurityUtil.authu_id();
        LOG.info("get restaurant {} for user {}", id, u_id);
        return checkNotFoundWithId(restaurantRepository.get(id, u_id), id);
    }

    public void delete(int id) {
        int u_id = SecurityUtil.authu_id();
        LOG.info("delete restaurant {} for user {}", id, u_id);
        checkNotFoundWithId(restaurantRepository.delete(id, u_id), id);
    }

    public Restaurant create(Restaurant restaurant) {
        int u_id = SecurityUtil.authu_id();
        checkNew(restaurant);
        LOG.info("create restaurant for user {}", u_id);
        Assert.notNull(restaurant, "restaurant must not be null");
        return restaurantRepository.save(restaurant, u_id);
    }

    public void update(Restaurant restaurant, int id) {
        int u_id = SecurityUtil.authu_id();
        assureIdConsistent(restaurant, id);
        LOG.info("update restaurant {} for user {}", id, u_id);
        Assert.notNull(restaurant, "restaurant must not be null");
        checkNotFoundWithId(restaurantRepository.save(restaurant, u_id), restaurant.id());
    }


    public List<Restaurant> getAll() {
        int u_id = SecurityUtil.authu_id();
       Set<Role> role = getUser(u_id).getRoles();
        boolean isAdmin = role.contains(Role.ADMIN);
        LOG.info("isAdmin = {} for user {}",isAdmin, u_id);
        if (isAdmin) {
            LOG.info("getAll restaurants for user {}", u_id);
            return restaurantRepository.getAll(u_id);
        }
        return restaurantRepository.getAll();
    }

    public void vote(int r_id) {
        int u_id = SecurityUtil.authu_id();
        LOG.info("vote user {} for restaurant {}", u_id, r_id);
        userRepository.vote(u_id, r_id);
    }

    public Restaurant getWithDishes(int id) {
        LOG.info("getWithMeals {}", id);
        return checkNotFoundWithId(restaurantRepository.getWithDishes(id), id);
    }
}
