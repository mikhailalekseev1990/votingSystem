package ru.javawebinar.restaurant.web;

import org.springframework.stereotype.Controller;
import ru.javawebinar.restaurant.repository.DishRepository;

@Controller
public class DishController {

    private final DishRepository dishRepository;

    public DishController(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }
}
