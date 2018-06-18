package com.javaschool.logistic.models;

import com.javaschool.logistic.model.Cargo;
import com.javaschool.logistic.model.City;

import javax.validation.constraints.NotNull;

public class Waypoint {


    @NotNull
    private Cargo cargo;

    @NotNull
    private City loadingCity;

    @NotNull
    private City unloadingCity;

    private boolean isExternal = false;

    private int external_id;

    public Waypoint() {
    }

    public Waypoint(Cargo cargo, City loadingCity, City unloadingCity, boolean isExternal, int external_id) {
        this.cargo = cargo;
        this.loadingCity = loadingCity;
        this.unloadingCity = unloadingCity;
        this.isExternal = isExternal;
        this.external_id = external_id;
    }

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

    public boolean isExternal() {
        return isExternal;
    }

    public void setExternal(boolean external) {
        isExternal = external;
    }

    public int getExternal_id() {
        return external_id;
    }

    public void setExternal_id(int external_id) {
        this.external_id = external_id;
    }
}
