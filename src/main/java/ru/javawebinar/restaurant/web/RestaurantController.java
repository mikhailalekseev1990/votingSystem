package ru.javawebinar.restaurant.web;

import org.springframework.stereotype.Controller;
import ru.javawebinar.restaurant.model.Restaurant;
import ru.javawebinar.restaurant.repository.RestaurantRepository;

import java.util.List;

@Controller
public class RestaurantController {

    private final RestaurantRepository repository;

    public RestaurantController(RestaurantRepository repository) {
        this.repository = repository;
    }
//
//
//
    public List<Restaurant> getAll() {
        return repository.getAll();
    }

    public Restaurant get(int id) {
        return repository.get(id);
    }

}
