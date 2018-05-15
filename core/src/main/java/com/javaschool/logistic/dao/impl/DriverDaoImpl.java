package com.javaschool.logistic.dao.impl;

import com.javaschool.logistic.dao.api.DriverDao;
import com.javaschool.logistic.model.Driver;
import com.javaschool.logistic.model.Truck;
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

    @Override
    public int getLastId() {
        int id;
        try {
            id=(int) getEntityManager()
                    .createQuery("SELECT MAX(u.driver_id) FROM Driver u")
                    .getSingleResult();
        }catch (NullPointerException e){
            return 1;
        }
        return id;
    }

    @Override
    public List<Driver> findSuitableDrivers(int distance, int avgSpeed, int shift, int mounth, int utoday, Truck truck) {

        return getEntityManager()
                .createQuery("SELECT u FROM Driver u WHERE " +
                        "NOT(((:mounth-u.worked_time)/:shift<=:utoday) " +
                        "AND (:distance/(:avgSpeed*:shift)>=(:mounth-u.worked_time)/:shift)) " +
                        "AND (u.city.city_id =:city_id)" +
                        "AND (u.user.enabled=true)")
                .setParameter("distance",(double) distance)
                .setParameter("avgSpeed",(double) avgSpeed)
                .setParameter("shift", shift)
                .setParameter("mounth", mounth)
                .setParameter("utoday", utoday)
                .setParameter("city_id", truck.getCity().getCity_id())
                .getResultList();

    }

    @Override
    public List<Driver> findAll() {
        return getEntityManager()
                .createQuery("SELECT u FROM Driver u WHERE u.user.enabled = true ")
                .getResultList();
    }


}
