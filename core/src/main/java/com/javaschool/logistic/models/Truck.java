package com.javaschool.logistic.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Getter
@Setter
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

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "city_id")
    private City city;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @OneToMany(mappedBy = "truck", cascade = CascadeType.ALL)
    private List<Driver> drivers;

    @OneToMany(mappedBy = "truck")
    private List<OrderHistory> orderHistory;


    @Override
    public String toString() {
        return "Truck{" +
                "truck_id=" + truck_id +
                ", reg_number='" + reg_number + '\'' +
                ", max_drivers=" + max_drivers +
                ", capacity=" + capacity +
                ", status=" + status +
                '}';
    }
}
