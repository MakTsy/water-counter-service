package com.cloudlabs.watercounterservice.repo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "user")
@NoArgsConstructor
public final class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    private String email;
    private String name;
    private double weight;

    public User(String email, String name, double weight) {
        this.email = email;
        this.name = name;
        this.weight = weight;
    }
}
