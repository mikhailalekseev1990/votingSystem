package ru.javawebinar.restaurant.repository;

import ru.javawebinar.restaurant.model.Dish;

import java.util.List;

public interface DishRepository {
    Dish save(Dish dish, int res_id);

    boolean delete(int id, int res_id);

    Dish get(int id, int res_id);

    List<Dish> getAll(int res_id);
}
