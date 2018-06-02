package com.javaschool.logistic.models;

import java.io.Serializable;
import java.util.List;

public class JsonResponse implements Serializable {

    private List<ScoreboardRow> rows;

    private int driversTotal;
    private int driversBusy;
    private int trucksTotal;
    private int trucksOrder;
    private int trucksBroken;

    public List<ScoreboardRow> getRows() {
        return rows;
    }

    public void setRows(List<ScoreboardRow> rows) {
        this.rows = rows;
    }

    public int getDriversTotal() {
        return driversTotal;
    }

    public void setDriversTotal(int driversTotal) {
        this.driversTotal = driversTotal;
    }

    public int getDriversBusy() {
        return driversBusy;
    }

    public void setDriversBusy(int driversBusy) {
        this.driversBusy = driversBusy;
    }

    public int getTrucksTotal() {
        return trucksTotal;
    }

    public void setTrucksTotal(int trucksTotal) {
        this.trucksTotal = trucksTotal;
    }

    public int getTrucksOrder() {
        return trucksOrder;
    }

    public void setTrucksOrder(int trucksOrder) {
        this.trucksOrder = trucksOrder;
    }

    public int getTrucksBroken() {
        return trucksBroken;
    }

    public void setTrucksBroken(int trucksBroken) {
        this.trucksBroken = trucksBroken;
    }
}
