package com.javaschool.logistic.service.impl;

import com.javaschool.logistic.dao.api.DriverDao;
import com.javaschool.logistic.model.Driver;
import com.javaschool.logistic.model.Truck;
import com.javaschool.logistic.service.api.DriverService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.core.env.Environment;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class DriverServiceImplTest {


    @Mock
    private DriverDao driverDao;

    @Mock
    private Environment environment;

    @Mock
    private AmqpTemplate amqpTemplate;

    @InjectMocks
    private DriverService driverService = new DriverServiceImpl();


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }


    private Driver getDriver(){
        Driver driver = new Driver();
        driver.setLast_name("Ivanov");
        driver.setFirst_name("Ivan");
        driver.setWorked_time(0);
        driver.setStatus(Driver.Status.REST);

        return driver;
    }


    @Test
    public void createDriver() {

        Driver driver = getDriver();

        doNothing().when(driverDao).create(any(Driver.class));
        doNothing().when(amqpTemplate).convertAndSend(any());
        when(driverDao.getLastId()).thenReturn(0);

        driverService.createDriver(driver);

        assertEquals(driver.getPersonal_code(),"d1");
        verify(driverDao).create(any(Driver.class));
    }

    @Test
    public void updateDriver() {

        Driver driverWork = getDriver();
        driverWork.setStatus(Driver.Status.WORK);
        driverWork.setStart(new Date(1528563534000L));


        when(driverDao.findById(anyInt())).thenReturn(driverWork);

        Driver driverRest = getDriver();
        driverService.updateDriver(driverRest);
        assertNull(driverRest.getStart());
        assertNotSame(driverRest.getWorked_time(),0);

        when(driverDao.findById(anyInt())).thenReturn(driverRest);

        driverService.updateDriver(driverWork);
        assertNotNull(driverWork.getStart());
    }

    @Test
    public void findSuitableDrivers() {

        List<Driver> drivers = new LinkedList<>();
        drivers.add(getDriver());
        drivers.add(getDriver());

        when(driverDao.findSuitableDrivers(anyInt(),anyInt(),anyInt(),anyInt(),anyInt(),any(Truck.class))).thenReturn(drivers);
        when(environment.getProperty(anyString())).thenReturn("10");

        driverService.findSuitableDrivers(0,new Truck());

        verify(driverDao).findSuitableDrivers(anyInt(),anyInt(),anyInt(),anyInt(),anyInt(),any(Truck.class));
    }

    @Test
    public void setWorktimeForAll() {

        doNothing().when(driverDao).setWorktimeForAll(anyInt(),any(Date.class));
        driverService.setWorktimeForAll(anyInt(),any(Date.class));
        verify(driverDao).setWorktimeForAll(anyInt(),any(Date.class));

    }
}