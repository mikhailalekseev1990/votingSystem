package ru.javawebinar.restaurant.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.restaurant.model.Dish;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudDishRepository extends JpaRepository<Dish, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Dish d WHERE d.id=:d_id AND d.restaurant.id=:r_id")
    int delete(@Param("d_id") int d_id, @Param("r_id") int r_id);

    @Query("SELECT d FROM Dish d WHERE d.restaurant.id =:r_id ORDER BY d.id")
    List<Dish> getAll(@Param("r_id") int r_id);

    @Query("SELECT d FROM Dish d ORDER BY d.restaurant.id")
    List<Dish> getAll();

    @Query("SELECT d FROM Dish d JOIN FETCH d.restaurant WHERE d.id = ?1 and d.restaurant.id = ?2")
    Dish getWithRestaurant(int d_id, int r_id);
}
