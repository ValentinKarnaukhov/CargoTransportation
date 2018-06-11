package com.javaschool.logistic.service.impl;

import com.javaschool.logistic.dao.api.CityDao;
import com.javaschool.logistic.model.City;
import com.javaschool.logistic.model.Truck;
import com.javaschool.logistic.service.api.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * @author Valentin
 */
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityDao cityDao;


    @Override
    @Transactional
    public void createCity(City city) {
        cityDao.create(city);

    }


    @Override
    @Transactional
    public List<Truck> findAllTrucks(String cityName) {
        return cityDao.findAllTrucks(cityName);
    }


    @Override
    @Transactional
    public List<City> findAllCities() {
        return cityDao.findAll();
    }


}
