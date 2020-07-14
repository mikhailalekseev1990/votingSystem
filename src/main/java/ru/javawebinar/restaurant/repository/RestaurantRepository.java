package ru.javawebinar.restaurant.repository;

import ru.javawebinar.restaurant.model.Dish;
import ru.javawebinar.restaurant.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {
    Restaurant save(Restaurant restaurant, int userId);

    boolean delete(int id,int userId);

    Restaurant get(int id, int userId);

    List<Restaurant> getAll(int userId);
}
