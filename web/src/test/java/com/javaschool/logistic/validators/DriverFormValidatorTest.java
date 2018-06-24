package com.javaschool.logistic.validators;

import com.javaschool.logistic.models.Driver;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.validation.BindingResult;
import org.springframework.validation.MapBindingResult;


import java.util.HashMap;

import static org.junit.Assert.*;

public class DriverFormValidatorTest {

    @InjectMocks
    private DriverFormValidator driverFormValidator = new DriverFormValidator();

    private BindingResult bindingResult = new MapBindingResult(new HashMap<>(),"driver");

    private Driver getDriver(){
        Driver driver = new Driver();
        driver.setWorked_time(100);
        driver.setFirst_name("Ivan");
        driver.setLast_name("Ivanov");
        return driver;
    }

    @Test
    public void validate() {
       driverFormValidator.validate(getDriver(),bindingResult);
       assertEquals(bindingResult.getErrorCount(),2);
    }
}