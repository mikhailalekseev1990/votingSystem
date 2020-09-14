package ru.javawebinar.restaurant.web.absractController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import ru.javawebinar.restaurant.model.Dish;
import ru.javawebinar.restaurant.repository.DishRepository;
import ru.javawebinar.restaurant.repository.RestaurantRepository;
import ru.javawebinar.restaurant.web.security.SecurityUtil;

import java.util.List;

import static ru.javawebinar.restaurant.Utils.ValidationUtil.*;

public abstract class AbstractDishController {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractDishController.class);
    @Autowired
    DishRepository dishRepository;
    @Autowired
    RestaurantRepository restaurantRepository;

    public Dish get(int id, int r_id) {
        LOG.info("get dish {} for restaurant {}", id, r_id);
        int u_id = SecurityUtil.authUserId();
        checkNotFoundWithId(restaurantRepository.get(r_id, u_id), r_id);
        return checkNotFoundWithId(dishRepository.get(id, r_id), id);
    }

    public void delete(int id, int r_id) {
        LOG.info("delete dish {} for restaurant {}", id, r_id);
        int u_id = SecurityUtil.authUserId();
        checkNotFoundWithId(restaurantRepository.get(r_id, u_id), r_id);
        checkNotFoundWithId(dishRepository.delete(id, r_id), id);
    }

    public Dish create(Dish dish, int r_id) {
        LOG.info("create dish for restaurant {}", r_id);
        checkNew(dish);
        int u_id = SecurityUtil.authUserId();
        checkNotFoundWithId(restaurantRepository.get(r_id, u_id), r_id);
        Assert.notNull(dish, "dish must not be null");
        return dishRepository.save(dish, r_id);
    }

    public void update(Dish dish, int id, int r_id) {
        assureIdConsistent(dish, id);
        LOG.info("update dish {} for restaurant {}", id, r_id);
        Assert.notNull(dish, "dish must not be null");
        int u_id = SecurityUtil.authUserId();
        checkNotFoundWithId(restaurantRepository.get(r_id, u_id), r_id);
        checkNotFoundWithId(dishRepository.save(dish, r_id), dish.id());
    }

    public List<Dish> getAll(int r_id) {
        LOG.info("getAll dish for restaurant {}", r_id);
        int u_id = SecurityUtil.authUserId();
        checkNotFoundWithId(restaurantRepository.get(r_id, u_id), r_id);
        return dishRepository.getAll(r_id);
    }

}
