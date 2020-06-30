package ru.javawebinar.restaurant.web;

import org.springframework.stereotype.Controller;
import ru.javawebinar.restaurant.model.Dish;
import ru.javawebinar.restaurant.repository.DishRepository;

import java.util.List;

@Controller
public class DishController {

    private final DishRepository dishRepository;

    public DishController(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public List<Dish> getAllById(int res_id) {
        return dishRepository.getAll(res_id);
    }

    public List<Dish> getAll() {
        return dishRepository.getAll();
    }
}
