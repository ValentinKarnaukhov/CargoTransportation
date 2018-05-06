package com.javaschool.logistic.service.api;


import com.javaschool.logistic.model.Driver;

import java.util.List;

public interface DriverService {


    List<Driver> findAllDrivers();

    void createDriver(Driver driver);

    void deleteById(int driver_id);

    Driver findById(int driver_id);

    void updateDriver(Driver driver);

}
