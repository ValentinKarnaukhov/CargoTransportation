package com.javaschool.logistic.model;

import javax.persistence.*;

/**
 * @author Valentin
 */


@Entity
@Table(name = "DRIVER")
public class Driver {

    public enum Status{
        REST,
        WORK,
        DRIVE
    }


    @Id
    @GeneratedValue
    @Column(name = "driver_id", nullable = false)
    private int driver_id;

    @Column(name = "personal_code", unique = true, nullable = false)
    private String personal_code;

    @Column(name = "first_name", nullable = false)
    private String first_name;

    @Column(name = "last_name", nullable = false)
    private String last_name;

    @Column(name = "worked_time", nullable = false)
    private int worked_time=0;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status=Status.REST;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne
    @JoinColumn(name = "truck_id")
    private Truck truck;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;


    @OneToOne(cascade = {CascadeType.REMOVE, CascadeType.MERGE})
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    public int getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(int driver_id) {
        this.driver_id = driver_id;
    }

    public String getPersonal_code() {
        return personal_code;
    }

    public void setPersonal_code(String personal_code) {
        this.personal_code = personal_code;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getWorked_time() {
        return worked_time;
    }

    public void setWorked_time(int worked_time) {
        this.worked_time = worked_time;
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

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
