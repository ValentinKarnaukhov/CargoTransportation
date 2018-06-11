package com.javaschool.logistic.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author Valentin
 */


@Entity
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

    public List<Cargo> getCargoes() {
        return cargoes;
    }

    public void setCargoes(List<Cargo> cargoes) {
        this.cargoes = cargoes;
    }


    public List<OrderWaypoint> getOrderWaypoints() {
        return orderWaypoints;
    }

    public void setOrderWaypoints(List<OrderWaypoint> orderWaypoints) {
        this.orderWaypoints = orderWaypoints;
    }

    public OrderHistory getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(OrderHistory orderHistory) {
        this.orderHistory = orderHistory;
    }

    @Override
    public String toString() {
        return "Order{" +
                "order_id=" + order_id +
                ", truck=" + truck +
                ", complete=" + complete +
                '}';
    }
}
