package ru.javawebinar.restaurant.repository.JPA;

import org.springframework.stereotype.Repository;
import ru.javawebinar.restaurant.model.Dish;
import ru.javawebinar.restaurant.model.Restaurant;
import ru.javawebinar.restaurant.repository.DishRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaDishRepository implements DishRepository {

    @PersistenceContext
    EntityManager manager;

    @Override
    @Transactional
    public Dish save(Dish dish, int restId) {
        dish.setRestaurant(manager.getReference(Restaurant.class, restId));
        if (dish.isNew()) {
            manager.persist(dish);
            return dish;
        } else {
            return get(dish.getId(), restId) == null ? null : manager.merge(dish);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int restId) {
        return manager.createNamedQuery(Dish.DELETE)
                .setParameter(1, id)
                .setParameter(2, restId)
                .executeUpdate() != 0;
    }

    @Override
    public Dish get(int id, int restId) {
        Dish dish = manager.find(Dish.class, id);
        return dish != null && dish.getRestaurant().getId() == restId ? dish : null;
    }

    @Override
    public List<Dish> getAll(int restId) {
        return manager.createNamedQuery(Dish.GET_ALL_FOR_RESTAURANT, Dish.class)
                .setParameter("restId", restId)
                .getResultList();
    }

    @Override
    public List<Dish> getAll() {
        return manager.createNamedQuery(Dish.GET_ALL, Dish.class)
                .getResultList();
    }
}
