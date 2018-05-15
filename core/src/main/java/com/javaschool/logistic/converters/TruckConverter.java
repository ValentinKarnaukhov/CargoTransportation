package com.javaschool.logistic.converters;



import com.javaschool.logistic.dao.api.TruckDao;
import com.javaschool.logistic.model.Truck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TruckConverter implements Converter<Object, Truck> {


    @Autowired
    private TruckDao truckDao;

    @Override
    public Truck convert(Object o) {

        int truck_id = Integer.parseInt((String)o);

        return truckDao.findById(truck_id);

    }


}
