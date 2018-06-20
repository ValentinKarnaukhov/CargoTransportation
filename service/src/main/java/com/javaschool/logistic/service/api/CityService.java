package com.javaschool.logistic.service.api;

import com.javaschool.logistic.models.City;
import com.javaschool.logistic.models.Truck;

import java.util.List;

public interface CityService {

    void createCity(City city);

    List<Truck> findAllTrucks(String cityName);

    List<City> findAllCities();
}
