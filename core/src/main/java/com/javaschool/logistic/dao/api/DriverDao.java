package com.javaschool.logistic.dao.api;

import com.javaschool.logistic.model.Driver;

import java.util.List;

public interface DriverDao extends GenericDao<Driver> {

    void deleteById(int id);

    Driver findById(int id);

    int getLastId();
}
