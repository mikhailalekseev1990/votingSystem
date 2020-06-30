package ru.javawebinar.restaurant.web;

import org.springframework.stereotype.Controller;
import ru.javawebinar.restaurant.model.Dish;
import ru.javawebinar.restaurant.model.Restaurant;
import ru.javawebinar.restaurant.repository.DishRepository;
import ru.javawebinar.restaurant.repository.RestaurantRepository;

import java.util.List;

import static ru.javawebinar.restaurant.Utils.ValidationUtil.assureIdConsistent;

@Controller
public class RestaurantController {

    private final RestaurantRepository restaurantRepository;

    private final DishRepository dishRepository;

    public RestaurantController(RestaurantRepository restaurantRepository, DishRepository dishRepository) {
        this.restaurantRepository = restaurantRepository;
        this.dishRepository = dishRepository;
    }

    public List<Dish> getAllDish( ){
        return dishRepository.getAll();
    }

    public List<Dish> getAllDish(int res_id){
        return dishRepository.getAll(res_id);
    }


    public List<Restaurant> getAll() {
        return restaurantRepository.getAll();
    }

    public Restaurant get(int id) {
        return restaurantRepository.get(id);
    }

    public Restaurant create(Restaurant restaurant){
       return restaurantRepository.save(restaurant);
    }

    public void update(Restaurant restaurant, int id){
        assureIdConsistent(restaurant, id);
        restaurantRepository.save(restaurant);
    }


}
