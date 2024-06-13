package com.MovieApp.user;

import com.MovieApp.review.Review;
import com.MovieApp.role.Role;

import jakarta.persistence.*;

import lombok.*;

import java.util.*;


@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Review> reviews = new ArrayList<>();

    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade={ CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH })
    @JoinTable(
            name= "user_role",
            joinColumns = { @JoinColumn(name="user_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name="role_id", referencedColumnName = "id") })
    private List<Role> roles = new ArrayList<>();

    public User(String name,
                String username,
                String password,
                String email,
                List<Role> roles
    ) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }
}
