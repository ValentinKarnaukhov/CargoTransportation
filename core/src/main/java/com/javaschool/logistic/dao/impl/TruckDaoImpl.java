package com.javaschool.logistic.dao.impl;

import com.javaschool.logistic.dao.api.TruckDao;
import com.javaschool.logistic.model.Truck;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;


@Repository
public class TruckDaoImpl extends GenericDaoImpl<Truck> implements TruckDao {

    private static final Logger LOGGER = Logger.getLogger(TruckDaoImpl.class);

    @Override
    public void deleteById(int id) {
        delete(findById(id));
    }

    @Override
    public Truck findById(int id) {
        try {
            return (Truck) getEntityManager()
                    .createQuery("SELECT u FROM Truck u LEFT JOIN FETCH u.drivers WHERE u.truck_id=:truck_id")
                    .setParameter("truck_id", id)
                    .getSingleResult();
        }catch (NoResultException e){
            LOGGER.error("Truck doesn't exist",e);
            return null;
        }
    }

    @Override
    public List<Truck> findSuitableTrucks(int weight) {
        return getEntityManager().
                createQuery("SELECT u FROM Truck u LEFT JOIN u.order o WHERE  u.capacity>=:weight AND u.enabled=true AND u.status=:status AND o is NULL AND u.drivers is EMPTY ")
                .setParameter("weight",weight)
                .setParameter("status",Truck.Status.OK)
                .getResultList();
    }



    @Override
    public List<Truck> findByNumber(String number) {
        return  getEntityManager()
                .createQuery("SELECT u FROM Truck u WHERE u.reg_number=:number")
                .setParameter("number",number)
                .getResultList();
    }

    @Override
    public List<Truck> findAllForAdmin() {
        return getEntityManager()
                .createQuery("SELECT u FROM Truck u")
                .getResultList();
    }

    @Override
    public List<Truck> findAll() {
        return getEntityManager()
                .createQuery("SELECT u FROM Truck u WHERE u.enabled = true ")
                .getResultList();
    }





}
