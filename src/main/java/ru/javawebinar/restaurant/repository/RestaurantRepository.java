package ru.javawebinar.restaurant.repository;

import ru.javawebinar.restaurant.model.Dish;
import ru.javawebinar.restaurant.model.Restaurant;
import ru.javawebinar.restaurant.model.User;

import java.util.List;

public interface RestaurantRepository {
    Restaurant save(Restaurant restaurant, int u_id);

    boolean delete(int id, int u_id);

    Restaurant get(int id, int u_id);

//    Restaurant get(int id);

    Restaurant getWithDishes(int id);

    Restaurant getWithUser(int id, int u_id);

    List<Restaurant> getAll(int u_id);//for users with role admin

    List<Restaurant> getAll();//for users with role user

    List<Restaurant> getAllWithDishes();

    List<Restaurant> getAllWithDishesByUser(int u_id);

}
