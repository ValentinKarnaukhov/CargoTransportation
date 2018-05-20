package com.javaschool.logistic.dao.api;

import com.javaschool.logistic.exeption.DaoException;
import com.javaschool.logistic.model.City;
import com.javaschool.logistic.model.Truck;

import java.util.List;

public interface CityDao extends GenericDao<City> {

    List<Truck> findAllTrucks(String cityName) throws DaoException;

    City findById(int city_id) throws DaoException;

}
