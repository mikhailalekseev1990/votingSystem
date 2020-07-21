package ru.javawebinar.restaurant.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = Restaurant.DELETE, query = "DELETE FROM Restaurant r where r.id=:id and r.user.id=:u_id"),
        @NamedQuery(name = Restaurant.GET_ALL, query = "SELECT r FROM Restaurant r WHERE r.user.id=:u_id ORDER BY r.id"),
        @NamedQuery(name = Restaurant.VOTE, query = "UPDATE Restaurant r SET r.voteSum = r.voteSum + 1 WHERE r.id = ?1 AND r.user.id = ?2")


})

@Entity
@Table(name = "restaurants")
public class Restaurant {
    public static final String DELETE = "Restaurant.delete";
    public static final String GET_ALL = "Restaurant.getAll";
    public static final String VOTE = "Restaurant.vote";

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = 100_000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    private Integer id;

    @Column(name = "name", unique = true, nullable = false)
    @NotBlank
    private String name;

    @Column(name = "voteSum")
    @NotNull
    private int voteSum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private User user;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "restaurant")//, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @OrderBy("id")
    private List<Dish> dishes;

    public Restaurant() {

    }

    public Restaurant(String name) {
        this.name = name;
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

    public int getVoteSum() {
        return voteSum;
    }

    public void setVoteSum(int voteSum) {
        this.voteSum = voteSum;
    }

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
}
