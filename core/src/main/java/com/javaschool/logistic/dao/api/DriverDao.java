package com.javaschool.logistic.dao.api;

import com.javaschool.logistic.models.Driver;
import com.javaschool.logistic.models.Truck;

import java.util.Date;
import java.util.List;

public interface DriverDao extends GenericDao<Driver> {

    void deleteById(int driver_id);

    Driver findById(int id);

    int getLastId() ;

    List<Driver> findSuitableDrivers(int distance, int avgSpeed, int shift, int mounth, int utoday, Truck truck);

    void setWorktimeForAll(int time, Date date);
}
