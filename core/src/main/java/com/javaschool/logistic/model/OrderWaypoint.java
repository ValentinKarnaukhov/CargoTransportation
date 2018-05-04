package com.javaschool.logistic.model;

import javax.persistence.*;


/**
 * @author Valentin
 */

@Entity
@Table(name = "ORDER_WAYPOINT")
public class OrderWaypoint {

    public enum Operation{
        LOADING,
        UNLOADING
    }

    public enum Status{
        PROGRESS,
        DONE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_waypoint_id", nullable = false)
    private int order_waypoint_id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id")
    private City city;

    @OneToOne(mappedBy = "orderWaypoint")
    private Cargo cargo;

    @Enumerated(EnumType.STRING)
    @Column(name = "operation", nullable = false)
    private Operation operation;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    public int getOrder_waypoint_id() {
        return order_waypoint_id;
    }

    public void setOrder_waypoint_id(int order_waypoint_id) {
        this.order_waypoint_id = order_waypoint_id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
