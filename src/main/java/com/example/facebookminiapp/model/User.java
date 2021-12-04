package com.example.facebookminiapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(name = "users_email_contraint", columnNames = "email")})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "firstname", nullable = false, columnDefinition = "VARCHAR(45)")
    private String firstname;

    @Column(name = "lastname", nullable = false, columnDefinition = "VARCHAR(45)")
    private String lastname;

    @Column(name = "password", nullable = false, columnDefinition = "VARCHAR(45)")
    private String password;

    @Column(name = "email", nullable = false, columnDefinition = "VARCHAR(45)")
    private String email;

    @Column(name = "dateOfBirth", nullable = false, columnDefinition = "VARCHAR(45)")
    private String dob;

    @Column(name = "gender", nullable = false, columnDefinition = "VARCHAR(45)")
    private String gender;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Post> post;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    @OneToOne(mappedBy = "user")
    private Likes myLike;

}