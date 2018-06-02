package com.javaschool.logistic.models;

import java.util.List;

public class ScoreboardRow {

    private int number;
    private String cityFrom;
    private String cityTo;
    private String truck;
    private int cargoes;
    private List<String> drivers;
    private boolean status;

    public ScoreboardRow(int number, String cityFrom, String cityTo, String truck, int cargoes, boolean status) {
        this.number = number;
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
        this.truck = truck;
        this.cargoes = cargoes;
        this.status = status;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(String cityFrom) {
        this.cityFrom = cityFrom;
    }

    public String getCituTo() {
        return cityTo;
    }

    public void setCituTo(String cituTo) {
        this.cityTo = cituTo;
    }

    public String getTruck() {
        return truck;
    }

    public void setTruck(String truck) {
        this.truck = truck;
    }

    public int getCargoes() {
        return cargoes;
    }

    public void setCargoes(int cargoes) {
        this.cargoes = cargoes;
    }

    public List<String> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<String> drivers) {
        this.drivers = drivers;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
