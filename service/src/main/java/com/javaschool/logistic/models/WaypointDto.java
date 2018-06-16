package com.javaschool.logistic.models;

import java.io.Serializable;

public class WaypointDto implements Serializable {

    private int point_id;
    private String cargo_number;
    private String cargo_name;
    private int weight;
    private String city;
    private String status;

    public WaypointDto() {
    }

    public WaypointDto(int point_id, String cargo_number, String name, int weight, String city, String status) {
        this.point_id = point_id;
        this.cargo_number = cargo_number;
        this.cargo_name = name;
        this.weight = weight;
        this.city = city;
        this.status= status;
    }

    public int getPoint_id() {
        return point_id;
    }

    public void setPoint_id(int point_id) {
        this.point_id = point_id;
    }

    public String getCargo_number() {
        return cargo_number;
    }

    public void setCargo_number(String cargo_number) {
        this.cargo_number = cargo_number;
    }

    public String getCargo_name() {
        return cargo_name;
    }

    public void setCargo_name(String cargo_name) {
        this.cargo_name = cargo_name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
