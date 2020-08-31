package ru.javawebinar.restaurant.repository.datajpa;

import org.springframework.stereotype.Repository;
import ru.javawebinar.restaurant.model.Dish;
import ru.javawebinar.restaurant.repository.DishRepository;

import java.util.List;

@Repository
public class DataJpaDishRepository implements DishRepository {

    private final CrudDishRepository crudDishRepository;
    private final CrudRestaurantRepository crudRestaurantRepository;

    public DataJpaDishRepository(CrudDishRepository crudDishRepository, CrudRestaurantRepository restaurantRepository) {
        this.crudDishRepository = crudDishRepository;
        this.crudRestaurantRepository = restaurantRepository;
    }

    @Override
    public Dish save(Dish dish, int r_id) {
        if (!dish.isNew() && get(dish.getId(), r_id) == null) {
            return null;
        }
        dish.setRestaurant(crudRestaurantRepository.getOne(r_id));
        return crudDishRepository.save(dish);
    }

    @Override
    public boolean delete(int d_id, int r_id) {
        return crudDishRepository.delete(d_id, r_id) != 0;
    }

    @Override
    public Dish get(int d_id, int r_id) {
        return crudDishRepository.findById(d_id).filter(dish -> dish.getRestaurant().getId() == r_id)
                .orElse(null);
    }

    @Override
    public List<Dish> getAll(int r_id) {
        return crudDishRepository.getAll(r_id);
    }

    @Override
    public List<Dish> getAll( ) {
        return crudDishRepository.getAll();
    }

    public Dish getWithRestaurant(int d_id, int r_id){
        return crudDishRepository.getWithRestaurant(d_id, r_id);
    }

}
