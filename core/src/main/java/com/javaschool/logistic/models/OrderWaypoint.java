package com.javaschool.logistic.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Getter
@Setter
@Table(name = "ORDER_WAYPOINT")
public class OrderWaypoint implements Serializable {

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

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "cargo_id")
    private Cargo cargo;

    @Enumerated(EnumType.STRING)
    @Column(name = "operation", nullable = false)
    private Operation operation;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status = Status.PROGRESS;

    @Override
    public String toString() {
        return "OrderWaypoint{" +
                "order_waypoint_id=" + order_waypoint_id +
                ", operation=" + operation +
                ", status=" + status +
                '}';
    }
}
