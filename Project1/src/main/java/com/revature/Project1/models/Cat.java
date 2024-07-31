package com.revature.Project1.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "cats")
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "a_id", updatable = false)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column
    private String color;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;

}
