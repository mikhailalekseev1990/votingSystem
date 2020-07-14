package ru.javawebinar.restaurant.repository.JPA;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.restaurant.model.Restaurant;
import ru.javawebinar.restaurant.model.User;
import ru.javawebinar.restaurant.repository.RestaurantRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaRestaurantRepository implements RestaurantRepository {

    @PersistenceContext
    EntityManager manager;

    @Override
    @Transactional
    public Restaurant save(Restaurant restaurant, int userId) {
        restaurant.setUser(manager.getReference(User.class, userId));
        if (restaurant.isNew()) {
            manager.persist(restaurant);
            return restaurant;
        } else if (get(restaurant.id(), userId) == null) {
            return null;
        }
        return manager.merge(restaurant);
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return manager.createNamedQuery(Restaurant.DELETE)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .executeUpdate() != 0;
    }

    @Override
    public Restaurant get(int id, int userId) {
        Restaurant restaurant = manager.find(Restaurant.class, id);
        return restaurant != null && restaurant.getUser().getId() == userId ? restaurant : null;
    }

    @Override
    public List<Restaurant> getAll(int userId) {
        return manager.createNamedQuery(Restaurant.GET_ALL, Restaurant.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}
