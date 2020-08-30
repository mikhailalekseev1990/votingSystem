package ru.javawebinar.restaurant.repository.datajpa;

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
    @Query("DELETE FROM User u WHERE u.id=:id")
    int delete(@Param("id") int id);

    User getByEmail(String email);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.vote = ?1 WHERE u.id = ?2")
    void isVote(boolean vote, int id);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.vote_restaurant_id = ?1  WHERE u.id = ?2")
    void updateVoteId(int vote_restaurant_id, int id);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.voteTime = ?1  WHERE u.id = ?2")
    void updateVoteTime(LocalDateTime ldt, int id);

}