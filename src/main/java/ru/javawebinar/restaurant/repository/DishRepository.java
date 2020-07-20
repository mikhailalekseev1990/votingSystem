package ru.javawebinar.restaurant.repository;

import ru.javawebinar.restaurant.model.Dish;

import java.util.List;

public interface DishRepository {
    Dish save(Dish dish, int r_id);

    boolean delete(int id, int r_id);

    Dish get(int id, int r_id);

    List<Dish> getAll(int r_id);

    List<Dish> getAll();
}
