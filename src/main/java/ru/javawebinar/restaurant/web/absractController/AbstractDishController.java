package ru.javawebinar.restaurant.web.absractController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import ru.javawebinar.restaurant.model.Dish;
import ru.javawebinar.restaurant.model.Restaurant;
import ru.javawebinar.restaurant.repository.DishRepository;
import ru.javawebinar.restaurant.repository.RestaurantRepository;
import ru.javawebinar.restaurant.web.SecurityUtil;

import java.util.List;

import static ru.javawebinar.restaurant.Utils.ValidationUtil.*;

@Controller
public  class AbstractDishController {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractDishController.class);

    @Autowired
    DishRepository dishRepository;

    public Dish get(int id, int restId) {
        LOG.info("get dish {} for restaurant {}", id, restId);
        return checkNotFoundWithId(dishRepository.get(id, restId), id);
    }

    public void delete(int id, int restId) {
        LOG.info("delete dish {} for restaurant {}", id, restId);
        checkNotFoundWithId(dishRepository.delete(id, restId), id);
    }

    public Dish create(Dish dish, int restId) {
        LOG.info("create dish for restaurant {}",  restId);
        checkNew(dish);
        Assert.notNull(dish, "dish must not be null");
        return dishRepository.save(dish, restId);
    }

    public void update(Dish dish, int id,int restId) {
        int userId = SecurityUtil.authUserId();
        assureIdConsistent(dish, id);
        LOG.info("update dish {} for restaurant {}", id, restId);
        Assert.notNull(dish, "dish must not be null");
        checkNotFoundWithId(dishRepository.save(dish, restId), dish.id());
    }

    public List<Dish> getAll(int restId) {
        int userId = SecurityUtil.authUserId();
        LOG.info("getAll dish for restaurant {}", restId);
        return dishRepository.getAll(userId);
    }

}
