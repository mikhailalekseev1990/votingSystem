package ru.javawebinar.restaurant.repository;

import ru.javawebinar.restaurant.model.Dish;
import ru.javawebinar.restaurant.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {
    Restaurant save(Restaurant restaurant, int u_id);

    boolean delete(int id,int u_id);

    Restaurant get(int id, int u_id);

    List<Restaurant> getAll(int u_id);

    void vote(int id, int u_id);
}
