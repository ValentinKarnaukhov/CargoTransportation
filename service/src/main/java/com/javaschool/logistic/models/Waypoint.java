package com.javaschool.logistic.models;

import com.javaschool.logistic.model.Cargo;
import com.javaschool.logistic.model.City;

public class Waypoint {

    private Cargo cargo;
    private City loadingCity;
    private City unloadingCity;


    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public City getLoadingCity() {
        return loadingCity;
    }

    public void setLoadingCity(City loadingCity) {
        this.loadingCity = loadingCity;
    }

    public City getUnloadingCity() {
        return unloadingCity;
    }

    public void setUnloadingCity(City unloadingCity) {
        this.unloadingCity = unloadingCity;
    }
}
