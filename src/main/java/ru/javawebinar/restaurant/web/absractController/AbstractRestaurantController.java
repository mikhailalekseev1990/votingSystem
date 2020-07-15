package ru.javawebinar.restaurant.web.absractController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import ru.javawebinar.restaurant.model.Restaurant;
import ru.javawebinar.restaurant.repository.DishRepository;
import ru.javawebinar.restaurant.repository.RestaurantRepository;
import ru.javawebinar.restaurant.web.SecurityUtil;

import static ru.javawebinar.restaurant.Utils.ValidationUtil.*;

import java.util.List;

public abstract class AbstractRestaurantController {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractRestaurantController.class);

    @Autowired
    RestaurantRepository restaurantRepository;

    public Restaurant get(int id) {
        int userId = SecurityUtil.authUserId();
        LOG.info("get restaurant {} for user {}", id, userId);
        return checkNotFoundWithId(restaurantRepository.get(id, userId), id);
    }

    public void delete(int id) {
        int userId = SecurityUtil.authUserId();
        LOG.info("delete restaurant {} for user {}", id, userId);
        checkNotFoundWithId(restaurantRepository.delete(id, userId), id);
    }

    public Restaurant create(Restaurant restaurant) {
        int userId = SecurityUtil.authUserId();
        LOG.info("create restaurant for user {}", userId);
        Assert.notNull(restaurant, "restaurant must not be null");
        return restaurantRepository.save(restaurant, userId);
    }

    public void update(Restaurant restaurant, int id) {
        int userId = SecurityUtil.authUserId();
        LOG.info("update restaurant {} for user {}", id, userId);
        Assert.notNull(restaurant, "restaurant must not be null");
        checkNotFoundWithId(restaurantRepository.save(restaurant, userId), restaurant.id());
    }

    public List<Restaurant> getAll() {
        int userId = SecurityUtil.authUserId();
        LOG.info("getAll restaurants for user {}", userId);
        return restaurantRepository.getAll(userId);
    }
}
