package com.javaschool.logistic.converters;

import com.javaschool.logistic.dao.api.DriverDao;
import com.javaschool.logistic.dao.api.TruckDao;
import com.javaschool.logistic.model.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DriverConverter implements Converter<Object, Driver> {


    @Autowired
    private DriverDao driverDao;

    @Override
    public Driver convert(Object o) {
        Driver driver;
        try {
            driver = (Driver) o;
        }catch (ClassCastException e){
            if(o.equals(""))return null;
            int driver_id = Integer.parseInt((String)o);
            return driverDao.findById(driver_id);
        }
        return driver;
    }
}
