package ru.javawebinar.restaurant.web;

import org.springframework.stereotype.Controller;
import ru.javawebinar.restaurant.model.Dish;
import ru.javawebinar.restaurant.repository.DishRepository;

import java.util.List;

public class DishController {

    private final DishRepository dishRepository;

    public DishController(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public List<Dish> getAllById(int restId) {
        return dishRepository.getAll(restId);
    }

    public List<Dish> getAll() {
        return dishRepository.getAll();
    }
}
