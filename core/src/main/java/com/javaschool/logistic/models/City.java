package com.javaschool.logistic.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "CITY")
public class City implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "city_id", nullable = false)
    private int city_id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "latitude")
    private double  latitude;

    @Column(name = "longitude")
    private double longitude;

    @OneToMany(mappedBy = "city")
    private List<Truck> trucks;

    @OneToMany(mappedBy = "city")
    private List<Driver> drivers;

    @OneToMany(mappedBy = "city")
    private List<OrderWaypoint> orderWaypoints;

    @OneToMany(mappedBy = "cityFrom")
    private List<External> externalsFrom;

    @OneToMany(mappedBy = "cityTo")
    private List<External> externalsTo;

    @Override
    public String toString() {
        return "City{" +
                "city_id=" + city_id +
                ", name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
