package ru.javawebinar.restaurant.model;

import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import ru.javawebinar.restaurant.Utils.LocalDateTimePersistenceConverter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.EnumSet;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = User.DELETE, query = "DELETE FROM User u WHERE u.id=:id"),
        @NamedQuery(name = User.BY_EMAIL, query = "SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.roles WHERE u.email=?1"),
        @NamedQuery(name = User.ALL_SORTED, query = "SELECT u FROM User u ORDER BY u.name, u.email"),
        @NamedQuery(name = User.IsVOTE, query = "UPDATE User u SET u.vote = ?1 WHERE u.id = ?2"),
        @NamedQuery(name = User.UPDATE_VOTE_ID, query = "UPDATE User u SET u.vote_restaurant_id = ?1  WHERE u.id = ?2"),
        @NamedQuery(name = User.UPDATE_VOTE_TIME, query = "UPDATE User u SET u.voteTime = ?1  WHERE u.id = ?2")
})
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "email", name = "users_unique_email_idx")})
public class User {

    public static final String DELETE = "User.delete";
    public static final String BY_EMAIL = "User.getByEmail";
    public static final String ALL_SORTED = "User.getAllSorted";
    public static final String IsVOTE = "User.isVote";
    public static final String UPDATE_VOTE_ID = "User.update_vote_id";
    public static final String UPDATE_VOTE_TIME = "User.update_vote_time";

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = 100_000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    private Integer id;

    @Column(name = "name", nullable = false)
    @NotBlank
    @Size(min = 2, max = 100)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotBlank
    private String email;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(min = 5, max = 100)
    private String password;

    @Column(name = "registration", nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    private Date registration = new Date();

    @Column(name = "vote", nullable = false, columnDefinition = "bool default true")
    private boolean vote;

    @Column(name = "vote_time", nullable = false)
    @NotNull
    @Convert(converter = LocalDateTimePersistenceConverter.class)
    private LocalDateTime voteTime;

    @Column(name = "vote_restaurant_id", nullable = false)
    @NotNull
    private int vote_restaurant_id;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role"}, name = "user_roles_unique_idx")})
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles;

    public User() {
    }

    public User(User u){
        this(u.getId(), u.getName(), u.getEmail(), u.getPassword(), u.getRegistration(),u.isVote(), u.getVoteTime(),u.getVote_restaurant_id(), u.getRoles());
    }

    public User(Integer id, String name, String email, String password, Role role, Role... roles) {
        this(id, name, email, password,new Date(), true, LocalDateTime.now(), 0,  EnumSet.of(role, roles));
    }

    public User(Integer id, String name, String email, String password, Date registration, boolean vote, LocalDateTime voteTime, Integer vote_restaurant_id, Collection<Role> roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.registration = registration;
        this.vote = vote;
        this.voteTime = voteTime;
        this.vote_restaurant_id = vote_restaurant_id;
        setRoles(roles);
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistration() {
        return registration;
    }

    public void setRegistration(Date registration) {
        this.registration = registration;
    }

    public boolean isVote() {
        return vote;
    }

    public void setVote(boolean vote) {
        this.vote = vote;
    }

    public LocalDateTime getVoteTime() {
        return voteTime;
    }

    public void setVoteTime(LocalDateTime voteTime) {
        this.voteTime = voteTime;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? EnumSet.noneOf(Role.class) : EnumSet.copyOf(roles);
    }

    public Set<Role> getRoles() {
        return roles;
    }


    public int getVote_restaurant_id() {
        return vote_restaurant_id;
    }

    public void setVote_restaurant_id(int vote_restaurant_id) {
        this.vote_restaurant_id = vote_restaurant_id;
    }
}
