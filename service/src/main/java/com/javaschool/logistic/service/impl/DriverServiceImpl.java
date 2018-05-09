package com.javaschool.logistic.service.impl;

import com.javaschool.logistic.dao.api.DriverDao;
import com.javaschool.logistic.dao.api.UserDao;
import com.javaschool.logistic.model.Driver;
import com.javaschool.logistic.service.api.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Valentin
 */

@Service
@Transactional
public class DriverServiceImpl implements DriverService {

    @Autowired
    DriverDao driverDao;



    @Override
    public List<Driver> findAllDrivers() {
        return driverDao.findAll();
    }

    @Override
    public void createDriver(Driver driver) {
        driverDao.create(driver);
    }

    @Override
    public void deleteById(int driver_id) {
        driverDao.deleteById(driver_id);
    }

    @Override
    public Driver findById(int driver_id) {
        return driverDao.findById(driver_id);
    }

    @Override
    public void updateDriver(Driver driver) {
        driverDao.update(driver);
    }


}
