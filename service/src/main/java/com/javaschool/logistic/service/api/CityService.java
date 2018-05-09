package com.javaschool.logistic.service.api;

import com.javaschool.logistic.model.City;
import com.javaschool.logistic.model.Truck;

import java.util.List;

public interface CityService {

    void createCity(City city);

    List<Truck> findAllTrucks(String cityName);

    List<City> findAllCities();
}
