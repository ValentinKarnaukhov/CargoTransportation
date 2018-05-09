package com.javaschool.logistic.dao.api;

import com.javaschool.logistic.model.Truck;

public interface TruckDao extends GenericDao<Truck> {

    void deleteById(int id);

    Truck findById(int id);
}
