package ru.javawebinar.restaurant.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.restaurant.model.User;
import ru.javawebinar.restaurant.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

import static ru.javawebinar.restaurant.Utils.TimeUtil.*;

@Repository
public class DataJpaUserRepository implements UserRepository {
    private static final Sort SORT_NAME_EMAIL = Sort.by(Sort.Direction.ASC, "name", "email");

    private final CrudUserRepository crudRepository;

    public DataJpaUserRepository(CrudUserRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    @Override
    public User save(User user) {
        return crudRepository.save(user);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public User get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    @Override
    public User getByEmail(String email) {
        return crudRepository.getByEmail(email);
    }

    @Override
    public List<User> getAll() {
        return crudRepository.findAll(SORT_NAME_EMAIL);
    }


    @Override  //TODO right vote(after double click vote change)
    public void vote(int u_id, int r_id) {
        LocalDateTime now = LocalDateTime.now();
        User user = get(u_id);
        boolean newDay = isNewDay(user.getVoteTime(), now);
        if (newDay && !user.isVote()) { //
            crudRepository.isVote(true, u_id);
        }
        if (user.isVote() || (!user.isVote() && isCompared(now, deadLine(now)))) {
            crudRepository.isVote(false, u_id);
            crudRepository.updateVoteId(r_id, u_id);
            crudRepository.updateVoteTime(now, u_id);
        }
    }
}
