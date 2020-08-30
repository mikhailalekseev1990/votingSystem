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
    public boolean delete(int id, int r_id) {
        return crudDishRepository.delete(id, r_id) != 0;
    }

    @Override
    public Dish get(int id, int r_id) {
        return crudDishRepository.findById(id).filter(dish -> dish.getRestaurant().getId() == r_id)
                .orElse(null);
    }

    @Override
    public List<Dish> getAll(int r_id) {
        return crudDishRepository.getAll(r_id);
    }

    public Dish getWithRestaurant(int id, int rId){
        return crudDishRepository.getWithRestaurant(id, rId);
    }

}
