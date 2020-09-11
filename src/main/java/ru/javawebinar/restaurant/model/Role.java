package ru.javawebinar.restaurant.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,
    ADMIN,
    RESTAURANT_ADMIN;

    @Override
    public String getAuthority() {
        return "ROLE_" + name();
    }
}
