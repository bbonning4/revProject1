package com.revature.Project1.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "cats")
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_id", updatable = false)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column
    private String color;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name = "user_id", nullable = false) // ?
    private User owner;

    public Cat() {

    }
    public Cat(int id, String name, String color, User owner) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.owner = owner;
    }
}
