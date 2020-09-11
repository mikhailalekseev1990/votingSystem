package ru.javawebinar.restaurant.model;

public interface HasIdAndEmail extends HasId {
    String getEmail();
}