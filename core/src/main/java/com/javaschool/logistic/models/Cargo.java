package com.javaschool.logistic.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author Valentin
 */

@Entity
@Table(name = "CARGO")
@Getter
@Setter
public class Cargo implements Serializable {

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

    @Column(name = "external")
    private boolean external = false;

    @Override
    public String toString() {
        return "Cargo[" +
                "cargo_id=" + cargo_id +
                ", number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", status=" + status +
                ']';
    }
}
