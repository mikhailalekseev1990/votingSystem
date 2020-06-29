package ru.javawebinar.restaurant.repository;

import ru.javawebinar.restaurant.model.Dish;
import ru.javawebinar.restaurant.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {
//    Restaurant save(Restaurant restaurant);
//
//    boolean delete(int id);

    Restaurant get(int id);

    List<Restaurant> getAll();
}
