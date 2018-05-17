package com.javaschool.logistic.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Valentin
 */

@Entity
@Table(name = "CARGO")
public class Cargo {

    public enum Status{
        PREPARE,
        SHIPPED,
        DONE
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cargo_id")
    private int cargo_id;

    @Column(name = "number", nullable = false)
    private String number;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "weight", nullable = false)
    private int weight;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status = Status.PREPARE;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "order_id")
    private Order order;

    @OneToMany(mappedBy = "cargo")
    private List<OrderWaypoint> orderWaypoint;

    public int getCargo_id() {
        return cargo_id;
    }

    public void setCargo_id(int cargo_id) {
        this.cargo_id = cargo_id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<OrderWaypoint> getOrderWaypoint() {
        return orderWaypoint;
    }

    public void setOrderWaypoint(List<OrderWaypoint> orderWaypoint) {
        this.orderWaypoint = orderWaypoint;
    }
}
