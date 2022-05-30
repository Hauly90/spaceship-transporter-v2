package com.greenfox.mockexam.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Planet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private int dockingCapacity;

    public Planet(String name, int dockingCapacity) {
        this.name = name;
        this.dockingCapacity = dockingCapacity;
    }

    public Planet() {

    }

}
