package com.javaschool.logistic.dao.impl;

import com.javaschool.logistic.dao.api.TruckDao;
import com.javaschool.logistic.model.Truck;
import org.springframework.stereotype.Repository;


@Repository
public class TruckDaoImpl extends GenericDaoImpl<Truck> implements TruckDao {
    @Override
    public void deleteById(int id) {
        delete(findById(id));
    }

    @Override
    public Truck findById(int id) {
        return (Truck) getEntityManager()
                .createQuery("SELECT u FROM Truck u WHERE u.truck_id=:truck_id")
                .setParameter("truck_id", id)
                .getSingleResult();
    }
}
