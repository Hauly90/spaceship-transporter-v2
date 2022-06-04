package com.greenfox.mockexam.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name="shipDetails")
public class Ship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne //(cascade = CascadeType.ALL)
    private Planet planet;

    private String name;
    private double maxSpeed;
    private boolean isDocked;

    public Ship(String name, double maxSpeed, boolean isDocked, Planet planet) {
        this.name = name;
        this.maxSpeed = maxSpeed;
        this.isDocked = isDocked;
        this.planet = planet;
    }

    public Ship() {

    }

    @Override
    public String toString() {
        return "Name of the ship: " + name + ", id: " + String.valueOf(id);
    }
}
