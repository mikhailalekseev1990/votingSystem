package ru.javawebinar.restaurant.repository;

import ru.javawebinar.restaurant.model.User;

import java.util.List;

public interface UserRepository {
    User save(User user);

    // false if not found
    boolean delete(int id);

    // null if not found
    User get(int id);

    // null if not found
    User getByEmail(String email);

    List<User> getAll();

    void vote(int u_id , int r_id);

    User getWithRestaurants(int id);
}
