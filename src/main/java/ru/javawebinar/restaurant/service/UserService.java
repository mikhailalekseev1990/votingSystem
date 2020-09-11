package ru.javawebinar.restaurant.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.javawebinar.restaurant.model.User;
import ru.javawebinar.restaurant.repository.UserRepository;
import ru.javawebinar.restaurant.web.security.AuthorizedUser;

import java.util.List;

import static ru.javawebinar.restaurant.Utils.UserUtil.prepareToSave;
import static ru.javawebinar.restaurant.Utils.ValidationUtil.*;
import static ru.javawebinar.restaurant.Utils.ValidationUtil.checkNotFoundWithId;

@Service("userService")
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAll() {
        return userRepository.getAll();
    }

    public User get(int id) {
        return checkNotFoundWithId(userRepository.get(id), id);
    }

    public User create(User user) {
        checkNew(user);
        Assert.notNull(user, "user must not be null");
        return prepareAndSave(user);
    }

    public void update(User user, int id) {
        assureIdConsistent(user, id);
        Assert.notNull(user, "user must not be null");
        prepareAndSave(user);
    }

    public void delete(int id) {
        checkNotFoundWithId(userRepository.delete(id), id);
    }

    public User getByEmail(String email) {
        Assert.notNull(email, "email must not be null");
        return checkNotFound(userRepository.getByEmail(email), "email=" + email);
    }

    public User getWithRestaurants(int id) {
        return checkNotFoundWithId(userRepository.getWithRestaurants(id), id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.getByEmail(email.toLowerCase());
        if(user==null){
            throw new UsernameNotFoundException("User "+ email +" not found");
        }
        return new AuthorizedUser(user);
    }

    private User prepareAndSave(User user) {
        return userRepository.save(prepareToSave(user, passwordEncoder));
    }
}
