package com.javaschool.logistic.service.impl;

import com.javaschool.logistic.dao.api.DriverDao;
import com.javaschool.logistic.model.Driver;
import com.javaschool.logistic.service.api.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
        driver.setPersonal_code("d"+(driverDao.getLastId()+1));
        driverDao.create(driver);
    }

    public int getLastId(){
        return driverDao.getLastId();
    }

    @Override
    public void deleteById(int driver_id) {
        Driver driver = driverDao.findById(driver_id);
        driver.getUser().setEnabled(false);
        driverDao.update(driver);
    }

    @Override
    public Driver findById(int driver_id) {
        return driverDao.findById(driver_id);
    }

    @Override
    public void updateDriver(Driver driver) {

        Driver currentDriver = driverDao.findById(driver.getDriver_id());

        Date date = new Date();



        if(!driver.getStatus().equals(Driver.Status.REST)){
            driver.setStart(currentDriver.getStart());
        }

        if((driver.getStatus().equals(Driver.Status.DRIVE)||
                driver.getStatus().equals(Driver.Status.WORK))&&
                currentDriver.getStatus().equals(Driver.Status.REST)){

            driver.setStart(date);

        }

        if(driver.getStatus().equals(Driver.Status.REST)&&
                (currentDriver.getStatus().equals(Driver.Status.WORK)||
                currentDriver.getStatus().equals(Driver.Status.DRIVE))){
            int workedTime = (int) (date.getTime()-currentDriver.getStart().getTime())/1000/60/60;
            driver.setWorked_time(currentDriver.getWorked_time()+workedTime);
        }



        driver.setUser(currentDriver.getUser());
        driverDao.update(driver);
    }


}
