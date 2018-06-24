package com.javaschool.logistic.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "DRIVER")
public class Driver implements Serializable {

    public enum Status{
        REST,
        WORK,
        DRIVE
    }


    @Id
    @GeneratedValue
    @Column(name = "driver_id", nullable = false)
    private int driver_id;

    @Column(name = "personal_code", unique = true)
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

    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "truck_id")
    private Truck truck;

    @OneToOne(cascade = {CascadeType.REMOVE, CascadeType.MERGE})
    @JoinColumn(name="user_id")
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start")
    private Date start;

    @ManyToMany(mappedBy = "drivers")
    private List<OrderHistory> historyList;

    @Override
    public String toString() {
        return "Driver[" +
                "driver_id=" + driver_id +
                ", personal_code='" + personal_code + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", worked_time=" + worked_time +
                ", status=" + status +
                ']';
    }
}
