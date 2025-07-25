package com.thanhtule.blog.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
@NoArgsConstructor  // auto sinh constructor
@Getter   // auto create getter, setter
@Setter
public class User {
    @Id  //  primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // auto: default, jpa tu chon
    private int id;

    @Column(name="user_name", nullable=false, length=100)
    private String name;
    private String email;
    private String password;
    private String about;

    @OneToMany(mappedBy  = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Post> posts = new ArrayList<>();
}

