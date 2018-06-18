package com.javaschool.logistic.model;


import javax.persistence.*;

@Entity
@Table(name = "EXTERNAL")
public class External {

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

    public int getExternal_id() {
        return external_id;
    }

    public void setExternal_id(int external_id) {
        this.external_id = external_id;
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

    public City getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(City cityFrom) {
        this.cityFrom = cityFrom;
    }

    public City getCityTo() {
        return cityTo;
    }

    public void setCityTo(City cityTo) {
        this.cityTo = cityTo;
    }
}
