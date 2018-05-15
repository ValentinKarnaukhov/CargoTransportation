package com.javaschool.logistic.dao.api;

import com.javaschool.logistic.model.Truck;

import java.util.List;

public interface TruckDao extends GenericDao<Truck> {

    void deleteById(int id);

    Truck findById(int id);

    List findSuitableTrucks(int weight);

    List<Truck> findByNumber(String number);



}
