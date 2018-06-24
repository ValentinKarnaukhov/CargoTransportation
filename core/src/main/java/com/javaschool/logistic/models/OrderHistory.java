package com.javaschool.logistic.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
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


}
