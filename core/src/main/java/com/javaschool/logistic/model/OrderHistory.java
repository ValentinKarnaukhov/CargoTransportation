package com.javaschool.logistic.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "ORDER_HISTORY")
public class OrderHistory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private int history_id;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "truck_id")
    private Truck truck;

    @ManyToMany
    @JoinTable(name = "DRIVER_ORDER_HISTORY",
    joinColumns = { @JoinColumn(name = "history_id")},
    inverseJoinColumns = {@JoinColumn(name = "driver_id")})
    private List<Driver> drivers;

    public OrderHistory() {
    }

    public OrderHistory(Order order, Truck truck, List<Driver> drivers) {
        this.order = order;
        this.truck = truck;
        this.drivers = drivers;
    }

    public int getHistory_id() {
        return history_id;
    }

    public void setHistory_id(int history_id) {
        this.history_id = history_id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }

}
