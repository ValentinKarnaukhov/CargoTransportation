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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
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

    public List<External> getExternalsFrom() {
        return externalsFrom;
    }

    public void setExternalsFrom(List<External> externalsFrom) {
        this.externalsFrom = externalsFrom;
    }

    public List<External> getExternalsTo() {
        return externalsTo;
    }

    public void setExternalsTo(List<External> externalsTo) {
        this.externalsTo = externalsTo;
    }

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
