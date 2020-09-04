package ru.javawebinar.restaurant.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.CollectionUtils;
import ru.javawebinar.restaurant.Utils.LocalDateTimePersistenceConverter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "email", name = "users_unique_email_idx")})
@Getter
@Setter
@ToString
//@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = NONE, isGetterVisibility = NONE, setterVisibility = NONE)
public class User extends AbstractNamedEntity implements HasIdAndEmail{

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotBlank
    private String email;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(min = 4, max = 100)
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")//, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @OrderBy("id")
    private List<Restaurant> restaurants;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role"}, name = "user_roles_unique_idx")})
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles;

    public User() {
    }

    public User(User u) {
        this(u.getId(), u.getName(), u.getEmail(), u.getPassword(), u.getRegistration(), u.isVote(), u.getVoteTime(), u.getVote_restaurant_id(), u.getRoles());
    }

    public User(Integer id, String name, String email, String password, Role role, Role... roles) {
        this(id, name, email, password, new Date(), true, LocalDateTime.now(), 0, EnumSet.of(role, roles));
    }

    public User(Integer id, String name, String email, String password, Date registration, boolean vote, LocalDateTime voteTime, Integer vote_restaurant_id, Collection<Role> roles) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.registration = registration;
        this.vote = vote;
        this.voteTime = voteTime;
        this.vote_restaurant_id = vote_restaurant_id;
        setRoles(roles);
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? EnumSet.noneOf(Role.class) : EnumSet.copyOf(roles);
    }
}
