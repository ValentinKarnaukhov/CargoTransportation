package com.javaschool.logistic.converters;

import com.javaschool.logistic.dao.api.DriverDao;
import com.javaschool.logistic.models.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DriverConverter implements Converter<Object, Driver> {

    private DriverDao driverDao;

    @Autowired
    public DriverConverter(DriverDao driverDao) {
        this.driverDao = driverDao;
    }

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
