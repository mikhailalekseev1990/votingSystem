package ru.javawebinar.restaurant.repository.datajpa;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.restaurant.model.User;

import java.time.LocalDateTime;

@Transactional(readOnly = true)
public interface CrudUserRepository extends JpaRepository<User, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM User u WHERE u.id=:u_id")
    int delete(@Param("u_id") int u_id);

    User getByEmail(String email);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.vote = ?1 WHERE u.id = ?2")
    void isVote(boolean vote, int u_id);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.vote_restaurant_id = ?1  WHERE u.id = ?2")
    void updateVoteId(int vote_restaurant_id, int u_id);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.voteTime = ?1  WHERE u.id = ?2")
    void updateVoteTime(LocalDateTime ldt, int u_id);

    //    https://stackoverflow.com/a/46013654/548473
    @EntityGraph(attributePaths = {"restaurants"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT u FROM User u WHERE u.id=?1")
    User getWithRestaurants(int id);

}