package ru.javawebinar.restaurant.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "restaurants")
public class Restaurant {

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = 100_000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    private Integer id;

    @Column(name = "name", unique = true, nullable = false)
    @NotBlank
    private String name;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    @JsonIgnore
    private User user;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "restaurant")//, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @OrderBy("id")
    @JsonManagedReference
    private List<Dish> dishes;

    public Restaurant() {

    }

    public Restaurant(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Restaurant( String name) {
        this(null, name);
    }

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

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public int getVoteSum() {
//        return voteSum;
//    }
//
//    public void setVoteSum(int voteSum) {
//        this.voteSum = voteSum;
//    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id;
    }
}
