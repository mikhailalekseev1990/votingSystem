package ru.javawebinar.restaurant.web.security;

import org.springframework.security.core.GrantedAuthority;
import ru.javawebinar.restaurant.model.User;

import java.util.Collection;

public class AuthorizedUser extends org.springframework.security.core.userdetails.User {
    private User user;

    public AuthorizedUser(User user) {
        super(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true, user.getRoles());
        this.user = user;
    }

    public int getId() {
        return user.id();
    }

    public void update(User newUser){
        user = newUser;
    }
    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return user.toString();
    }
}
