package com.javaschool.logistic.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "EXTERNAL")
public class External implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "external_id")
    private int external_id;

    @Column(name = "name")
    private String name;

    @Column(name = "weight")
    private int weight;

    @ManyToOne
    @JoinColumn(name = "city_from")
    private City cityFrom;

    @ManyToOne
    @JoinColumn(name = "city_to")
    private City cityTo;

}
