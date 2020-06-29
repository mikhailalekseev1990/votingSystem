package ru.javawebinar.restaurant.repository.JPA;

import org.springframework.stereotype.Repository;
import ru.javawebinar.restaurant.model.Restaurant;
import ru.javawebinar.restaurant.repository.RestaurantRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class JpaRestaurantRepository implements RestaurantRepository {

    @PersistenceContext
    EntityManager manager;

    @Override
    public Restaurant get(int id) {
        return manager.find(Restaurant.class, id);
    }

    @Override
    public List<Restaurant> getAll() {
        return manager.createNamedQuery(Restaurant.GET_ALL, Restaurant.class).getResultList();
    }
}
