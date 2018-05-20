package com.javaschool.logistic.dao.api;

import com.javaschool.logistic.exeption.DaoException;
import com.javaschool.logistic.model.Truck;

import java.util.List;

public interface TruckDao extends GenericDao<Truck> {

    void deleteById(int id) throws DaoException;

    Truck findById(int id) throws DaoException;

    List findSuitableTrucks(int weight) throws DaoException;

    List<Truck> findByNumber(String number) throws DaoException;



}
