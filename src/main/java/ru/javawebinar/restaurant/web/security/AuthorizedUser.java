package ru.javawebinar.restaurant.web.security;

import ru.javawebinar.restaurant.model.User;

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
