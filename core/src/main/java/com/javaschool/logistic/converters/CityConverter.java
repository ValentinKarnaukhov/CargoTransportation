package com.javaschool.logistic.converters;

import com.javaschool.logistic.dao.api.CityDao;
import com.javaschool.logistic.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CityConverter implements Converter<Object, City> {

    private CityDao cityDao;

    @Autowired
    public CityConverter(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    @Override
    public City convert(Object o) {

        int city_id = Integer.parseInt((String)o);

        return cityDao.findById(city_id);

    }
}
