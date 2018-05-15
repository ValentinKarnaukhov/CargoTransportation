package com.javaschool.logistic.utils;

import com.javaschool.logistic.model.Driver;
import com.javaschool.logistic.service.api.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class DriversConverter {

    @Autowired
    DriverService driverService;

    public List<Driver> convert(List<Integer> drivers_id) {

        List<Driver> driverList = new LinkedList<>();

        for (int id : drivers_id) {
            driverList.add(driverService.findById(id));
        }
        return driverList;
    }


}
