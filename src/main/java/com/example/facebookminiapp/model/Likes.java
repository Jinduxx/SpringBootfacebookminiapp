package com.example.facebookminiapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;

    @OneToOne
    @JoinColumn(name = "postId", referencedColumnName = "postId")
    private Post post;
}