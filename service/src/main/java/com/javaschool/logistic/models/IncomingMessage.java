package com.javaschool.logistic.models;

import java.io.Serializable;

public class IncomingMessage implements Serializable {

    private int id;
    private int weight;
    private String cityTo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getCityTo() {
        return cityTo;
    }

    public void setCityTo(String cityTo) {
        this.cityTo = cityTo;
    }

    @Override
    public String toString() {
        return "IncomingMessage{" +
                "id=" + id +
                ", weight=" + weight +
                ", cityTo='" + cityTo + '\'' +
                '}';
    }
}
