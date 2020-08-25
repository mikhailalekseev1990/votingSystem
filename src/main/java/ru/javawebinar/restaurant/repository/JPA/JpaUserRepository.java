package ru.javawebinar.restaurant.repository.JPA;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.restaurant.model.User;
import ru.javawebinar.restaurant.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

import static ru.javawebinar.restaurant.Utils.TimeUtil.*;

@Repository
@Transactional(readOnly = true)
public class JpaUserRepository implements UserRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    @Transactional
    public User save(User user) {
        if (user.isNew()) {
            manager.persist(user);
            return user;
        } else {
            return manager.merge(user);
        }
    }

    @Override
    public User get(int id) {
        return manager.find(User.class, id);
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return manager.createNamedQuery(User.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public User getByEmail(String email) {
        List<User> users = manager.createNamedQuery(User.BY_EMAIL, User.class)
                .setParameter(1, email)
                .getResultList();
        return DataAccessUtils.singleResult(users);
    }

    @Override
    public List<User> getAll() {
        return manager.createNamedQuery(User.ALL_SORTED, User.class).getResultList();
    }


    @Override
    @Transactional
    public void vote(int u_id, int r_id) {
        LocalDateTime now = LocalDateTime.now();
        User user = get(u_id);
        LocalDateTime userVoteTime = user.getVoteTime();
        boolean newDay = isNewDay(user.getVoteTime(), now);
        if(newDay){ //
            manager.createNamedQuery(User.IsVOTE)
                    .setParameter(1, true)
                    .setParameter(2, u_id)
                    .executeUpdate();
        }
        if (user.isVote() || (!user.isVote() && isCompared(now, deadLine(now)))) {  //TODO take into consideration vote_time
            manager.createNamedQuery(User.IsVOTE)
                    .setParameter(1, false)
                    .setParameter(2, u_id)
                    .executeUpdate();
//            LOG.info("user {} vote {}", u_id, restaurant.getUser().isVote());
//            manager.createNamedQuery(Restaurant.VOTE)
//                    .setParameter(1, r_id)
//                    .setParameter(2, u_id)
//                    .executeUpdate();
            manager.createNamedQuery(User.UPDATE_VOTE_ID)
                    .setParameter(1, r_id)
                    .setParameter(2, u_id)
                    .executeUpdate();
            manager.createNamedQuery(User.UPDATE_VOTE_TIME)
                    .setParameter(1, now)
                    .setParameter(2, u_id)
                    .executeUpdate();
        }
    }
}

