package com.javaschool.logistic.service.impl;

import com.javaschool.logistic.dao.api.DriverDao;
import com.javaschool.logistic.models.Driver;
import com.javaschool.logistic.models.Truck;
import com.javaschool.logistic.service.api.DriverService;
import org.apache.log4j.Logger;
import org.springframework.amqp.AmqpConnectException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Service
@PropertySource(value = { "classpath:appConfig.properties" })
public class DriverServiceImpl implements DriverService {

    private static final Logger LOGGER = Logger.getLogger(DriverServiceImpl.class);

    private DriverDao driverDao;

    private Environment environment;

    private AmqpTemplate amqpTemplate;

    @Autowired
    public DriverServiceImpl(DriverDao driverDao, Environment environment, AmqpTemplate amqpTemplate) {
        this.driverDao = driverDao;
        this.environment = environment;
        this.amqpTemplate = amqpTemplate;
    }

    public DriverServiceImpl() {
    }

    @Override
    @Transactional
    public List<Driver> findAllDrivers() {
        return driverDao.findAll();
    }


    @Override
    @Transactional
    public void createDriver(Driver driver) {
        driver.setPersonal_code("d"+(driverDao.getLastId()+1));
        driverDao.create(driver);
        try {
            amqpTemplate.convertAndSend("infoQueue", "update");
        }catch (AmqpConnectException e){
            LOGGER.warn("Queue server not available", e);
        }
        LOGGER.info(driver+"has been created");
    }

    public int getLastId(){
        return driverDao.getLastId();
    }


    /**
     * driver no deletes from database, only changes enable status
     * @param driver_id driver id
     */
    @Override
    @Transactional
    public void deleteById(int driver_id) {
        Driver driver = driverDao.findById(driver_id);
        driver.getUser().setEnabled(false);
        driverDao.update(driver);
        try {
            amqpTemplate.convertAndSend("infoQueue", "update");
        }catch (AmqpConnectException e){
            LOGGER.warn("Queue server not available", e);
        }
        LOGGER.info(driver+"has been removed");
    }


    @Override
    @Transactional
    public Driver findById(int driver_id) {
        return driverDao.findById(driver_id);
    }


    /**
     * intelligent drivers updater, if driver has status REST and change on different then
     *  date field gets timestamp with start time
     *  if status changes to REST then remove timestamp and add time to worked time
     * @param driver driver id
     */
    @Override
    @Transactional
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
        LOGGER.info(driver+"has been updated");
        driverDao.update(driver);
    }


    /**
     * @param distance full distance in the road
     * @param truck truck for order
     * @return list drivers with satiable city and worked time
     */
    @Override
    @Transactional
    public List<Driver> findSuitableDrivers(int distance, Truck truck){
        return driverDao.findSuitableDrivers(distance,
                Integer.parseInt(environment.getProperty("avgSpeed")),
                Integer.parseInt(environment.getProperty("shift")),
                Integer.parseInt(environment.getProperty("mounth")),
                30-new GregorianCalendar().get(Calendar.DAY_OF_MONTH),truck);
    }


    @Override
    @Transactional
    public void setWorktimeForAll(int time, Date date) {
        driverDao.setWorktimeForAll(time,date);
    }


}
