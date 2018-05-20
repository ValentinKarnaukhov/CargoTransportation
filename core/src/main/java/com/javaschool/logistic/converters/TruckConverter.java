package com.javaschool.logistic.converters;



import com.javaschool.logistic.dao.api.CityDao;
import com.javaschool.logistic.dao.api.TruckDao;
import com.javaschool.logistic.model.Truck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TruckConverter implements Converter<Object, Truck> {


    @Autowired
    private TruckDao truckDao;



    @Override
    public Truck convert(Object o) {

        Truck truck;
        try {
            truck = (Truck) o;
        }catch (ClassCastException e){
            int truck_id = Integer.parseInt((String)o);
            return truckDao.findById(truck_id);
        }

        return truck;


    }


}