package com.javaschool.logistic.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author Valentin
 */

@Entity
@Table(name = "TRUCK")
public class Truck implements Serializable {

    public enum Status{
        OK,
        BROKEN
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "truck_id", nullable = false)
    private int truck_id;

    @Column(name = "reg_number", nullable = false, unique = true)
    private String reg_number;

    @Column(name = "max_drivers", nullable = false)
    private int max_drivers;

    @Column(name = "capacity", nullable = false)
    private int capacity;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "enabled")
    private boolean enabled = true;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @OneToMany(mappedBy = "truck")
    private List<Driver> drivers;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "truck_id")
    private Order order;

    public int getTruck_id() {
        return truck_id;
    }

    public void setTruck_id(int truck_id) {
        this.truck_id = truck_id;
    }

    public String getReg_number() {
        return reg_number;
    }

    public void setReg_number(String reg_number) {
        this.reg_number = reg_number;
    }

    public int getMax_drivers() {
        return max_drivers;
    }

    public void setMax_drivers(int max_drivers) {
        this.max_drivers = max_drivers;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
