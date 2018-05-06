package com.javaschool.logistic.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author Valentin
 */

@Entity
@Table(name = "CITY")
public class City implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "city_id", nullable = false)
    private int city_id;

    @Column(name = "name", nullable = false)
    public String name;

    @OneToMany(mappedBy = "city")
    private List<Truck> trucks;

    @OneToMany(mappedBy = "city")
    private List<Driver> drivers;

    @OneToMany(mappedBy = "city")
    private List<OrderWaypoint> orderWaypoints;

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Truck> getTrucks() {
        return trucks;
    }

    public void setTrucks(List<Truck> trucks) {
        this.trucks = trucks;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }

    public List<OrderWaypoint> getOrderWaypoints() {
        return orderWaypoints;
    }

    public void setOrderWaypoints(List<OrderWaypoint> orderWaypoints) {
        this.orderWaypoints = orderWaypoints;
    }
}
