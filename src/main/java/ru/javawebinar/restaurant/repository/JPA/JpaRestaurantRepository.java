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
    public Restaurant save(Restaurant restaurant, int u_id) {
        restaurant.setUser(manager.getReference(User.class, u_id));
        if (restaurant.isNew()) {
            manager.persist(restaurant);
            return restaurant;
        } else if (get(restaurant.id(), u_id) == null) {
            return null;
        }
        return manager.merge(restaurant);
    }

    @Override
    @Transactional
    public boolean delete(int id, int u_id) {
        return manager.createNamedQuery(Restaurant.DELETE)
                .setParameter("id", id)
                .setParameter("u_id", u_id)
                .executeUpdate() != 0;
    }

    @Override
    public Restaurant get(int id, int u_id) {
        Restaurant restaurant = manager.find(Restaurant.class, id);
        return restaurant != null && restaurant.getUser().getId() == u_id ? restaurant : null;
    }

    @Override
    public List<Restaurant> getAll(int u_id) {
        return manager.createNamedQuery(Restaurant.GET_ALL, Restaurant.class)
                .setParameter("u_id", u_id)
                .getResultList();
    }
}
