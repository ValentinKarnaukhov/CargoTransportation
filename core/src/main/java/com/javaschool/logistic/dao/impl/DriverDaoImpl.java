package com.javaschool.logistic.dao.impl;

import com.javaschool.logistic.dao.api.DriverDao;
import com.javaschool.logistic.model.Driver;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Valentin
 */
@Repository
public class DriverDaoImpl extends GenericDaoImpl<Driver> implements DriverDao {


    @Override
    public void deleteById(int driver_id) {
        delete(findById(driver_id));
    }

    @Override
    public Driver findById(int driver_id) {
        return (Driver) getEntityManager()
                .createQuery("SELECT u FROM Driver u WHERE u.driver_id=:driver_id")
                .setParameter("driver_id", driver_id)
                .getSingleResult();
    }




}
