package ru.javawebinar.restaurant.web.absractController;

import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.restaurant.repository.DishRepository;

public abstract class AbstractRestaurantController {

    @Autowired
    DishRepository dishRepository;
}
