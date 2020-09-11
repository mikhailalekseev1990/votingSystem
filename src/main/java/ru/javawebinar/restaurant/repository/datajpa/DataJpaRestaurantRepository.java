package ru.javawebinar.restaurant.repository.datajpa;

import org.springframework.stereotype.Repository;
import ru.javawebinar.restaurant.model.Restaurant;
import ru.javawebinar.restaurant.repository.RestaurantRepository;

import java.util.List;

@Repository
public class DataJpaRestaurantRepository implements RestaurantRepository {

    private final CrudRestaurantRepository crudRestaurantRepository;
    private final CrudUserRepository crudUserRepository;

    public DataJpaRestaurantRepository(CrudRestaurantRepository crudRestaurantRepository, CrudUserRepository crudUserRepository) {
        this.crudRestaurantRepository = crudRestaurantRepository;
        this.crudUserRepository = crudUserRepository;
    }

    @Override
    public Restaurant save(Restaurant restaurant, int u_id) {
        if (!restaurant.isNew() && get(restaurant.getId(), u_id) == null) {
            return null;
        }
        restaurant.setUser(crudUserRepository.getOne(u_id));
        return crudRestaurantRepository.save(restaurant);
    }

    @Override
    public boolean delete(int r_id, int u_id) {
        return crudRestaurantRepository.delete(r_id, u_id) != 0;
    }

    @Override
    public Restaurant get(int r_id, int u_id) {
        return crudRestaurantRepository.findById(r_id)
                .filter(restaurant -> restaurant.getUser().getId() == u_id).orElse(null);
    }

    @Override
    public List<Restaurant> getAll(int u_id) {
        return crudRestaurantRepository.getAll(u_id);
    }

    @Override
    public List<Restaurant> getAll() {
        return crudRestaurantRepository.getAll();
    }

    @Override
    public Restaurant getWithDishes(int r_id) {
        return crudRestaurantRepository.getWithDishes(r_id);
    }

    @Override
    public List<Restaurant> getAllWithDishes() {
        return crudRestaurantRepository.getAllWithDishes();
    }

    @Override
    public List<Restaurant> getAllWithDishesByUser(int u_id){
        return crudRestaurantRepository.getAllWithDishesByUser(u_id);
    }

    public Restaurant getWithUser(int r_id, int u_id) {
        return crudRestaurantRepository.getWithUser(r_id, u_id);
    }

}
