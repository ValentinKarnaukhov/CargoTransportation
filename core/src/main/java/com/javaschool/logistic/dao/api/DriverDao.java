package com.javaschool.logistic.dao.api;

import com.javaschool.logistic.model.Driver;
import com.javaschool.logistic.model.Truck;

import java.util.List;

public interface DriverDao extends GenericDao<Driver> {

    void deleteById(int id);

    Driver findById(int id);

    int getLastId() ;

    List<Driver> findSuitableDrivers(int distance, int avgSpeed, int shift, int mounth, int utoday, Truck truck);
}
