package ru.javawebinar.restaurant.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NamedQueries({
        @NamedQuery(name = Dish.DELETE, query = "DELETE FROM Dish m where m.id=:id and m.restaurant.id=:restId"),
        @NamedQuery(name = Dish.GET_ALL_FOR_RESTAURANT, query = "SELECT d FROM Dish d WHERE d.restaurant.id=:restIid"),
        @NamedQuery(name = Dish.GET_ALL, query = "SELECT d FROM Dish d")

})


@Entity
@Table(name = "menu")
public class Dish {

    public static final String DELETE = "Dish.delete";
    public static final String GET_ALL = "Dish.getAll";
    public static final String GET_ALL_FOR_RESTAURANT = "Dish.getAllForRestaurant";

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = 100_000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    private Integer id;

    @Column(name = "dish", unique = true, nullable = false)
    @NotBlank
    private String name;

    @Column(name = "price", nullable = false)
    @NotNull
    private Integer price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Restaurant restaurant;

    public int id() {
        Assert.notNull(id, "Entity must has id");
        return id;
    }

    public boolean isNew() {
        return this.id == null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer dish_id) {
        this.id = dish_id;
    }

    public String getDish() {
        return name;
    }

    public void setDish(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
