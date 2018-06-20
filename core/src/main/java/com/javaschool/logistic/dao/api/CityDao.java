package com.javaschool.logistic.dao.api;

import com.javaschool.logistic.models.City;
import com.javaschool.logistic.models.Truck;

import java.util.List;

public interface CityDao extends GenericDao<City> {

    List<Truck> findAllTrucks(String cityName);

    City findById(int city_id);

}
