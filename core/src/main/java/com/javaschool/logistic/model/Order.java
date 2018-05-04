package com.javaschool.logistic.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author Valentin
 */


@Entity
@Table(name = "ORDERS")
public class Order {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private int order_id;

    @OneToOne(mappedBy = "order")
    private Truck truck;

    @Column(name = "is_complete", nullable = false)
    private boolean complete;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "begin")
    private Date begin;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ended")
    private Date ended;


    @OneToMany(mappedBy = "order")
    private List<Cargo> cargoes;

    @OneToMany(mappedBy = "order")
    private List<Driver> drivers;

    @OneToMany(mappedBy = "order")
    private List<OrderWaypoint> orderWaypoints;

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnded() {
        return ended;
    }

    public void setEnded(Date ended) {
        this.ended = ended;
    }

    public List<Cargo> getCargoes() {
        return cargoes;
    }

    public void setCargoes(List<Cargo> cargoes) {
        this.cargoes = cargoes;
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