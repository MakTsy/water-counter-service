package com.cloudlabs.watercounterservice.repo.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "water_for_user")
@NoArgsConstructor
public class WaterForUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    long userId;
    private double waterExpected;
    private double waterActual;
    private LocalDate date;

    public WaterForUser(long userId, double waterExpected, double waterActual, LocalDate date) {
        this.userId = userId;
        this.waterExpected = waterExpected;
        this.waterActual = waterActual;
        this.date = date;
    }
}
