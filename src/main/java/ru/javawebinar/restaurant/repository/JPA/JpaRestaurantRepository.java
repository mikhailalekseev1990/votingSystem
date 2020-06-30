package ru.javawebinar.restaurant.repository.JPA;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
    public Restaurant save(Restaurant restaurant) {
        if (restaurant.isNew()) {
            manager.persist(restaurant);
            return restaurant;
        } else {
            return manager.merge(restaurant);
        }
    }

    @Override
    public boolean delete(int id) {
        return manager.createNamedQuery(Restaurant.DELETE, Restaurant.class).executeUpdate() != 0;
    }

    @Override
    public Restaurant get(int id) {
        return manager.find(Restaurant.class, id);
    }

    @Override
    public List<Restaurant> getAll() {
        return manager.createNamedQuery(Restaurant.GET_ALL, Restaurant.class).getResultList();
    }
}
