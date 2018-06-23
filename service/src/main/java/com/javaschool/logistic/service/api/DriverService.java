package com.javaschool.logistic.service.api;

import com.javaschool.logistic.models.Driver;
import com.javaschool.logistic.models.Truck;

import java.util.Date;
import java.util.List;

public interface DriverService {

    List<Driver> findAllDrivers();

    void createDriver(Driver driver);

    void deleteById(int driver_id);

    Driver findById(int driver_id);

    void updateDriver(Driver driver);

    int getLastId();

    List<Driver> findSuitableDrivers(int distance, Truck truck);

    void setWorktimeForAll(int time,Date date);

}
