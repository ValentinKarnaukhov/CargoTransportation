package com.javaschool.logistic.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "ORDERS")
public class Order implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private int order_id;


    @OneToOne(mappedBy = "order", cascade = CascadeType.REFRESH)
    private Truck truck;

    @Column(name = "is_complete", nullable = false)
    private boolean complete;


    @OneToMany(mappedBy = "order")
    private List<Cargo> cargoes;


    @OneToMany(mappedBy = "order", cascade = CascadeType.REFRESH)
    private List<OrderWaypoint> orderWaypoints;

    @OneToOne(mappedBy = "order")
    private OrderHistory orderHistory;

    @Override
    public String toString() {
        return "Order{" +
                "order_id=" + order_id +
                ", truck=" + truck +
                ", complete=" + complete +
                '}';
    }
}
