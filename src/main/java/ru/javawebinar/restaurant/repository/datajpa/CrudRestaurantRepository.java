package ru.javawebinar.restaurant.repository.datajpa;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.restaurant.model.Dish;
import ru.javawebinar.restaurant.model.Restaurant;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Restaurant r WHERE r.id=:id AND r.user.id=:uId")
    int delete(@Param("id") int id, @Param("uId") int uId);

    @Query("SELECT r FROM Restaurant r WHERE r.user.id=:uId ORDER BY r.id")
    List<Restaurant> getAll(@Param("uId") int uId);

    @Query("SELECT r FROM Restaurant r ORDER BY r.id DESC")
    List<Restaurant> getAll();


    @Query("SELECT r FROM Restaurant r JOIN FETCH r.user WHERE r.id = ?1 and r.user.id = ?2")
    Restaurant getWithUser(int id, int uId);

    //    https://stackoverflow.com/a/46013654/548473
    @EntityGraph(attributePaths = {"dishes"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT r FROM Restaurant r WHERE r.id=?1")
    Restaurant getWithDishes(int id);
}
