package com.javaschool.logistic.dao.impl;

import com.javaschool.logistic.dao.api.DriverDao;
import com.javaschool.logistic.models.Driver;
import com.javaschool.logistic.models.Truck;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.Date;
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
        try{
            return (Driver) getEntityManager()
                    .createQuery("SELECT u FROM Driver u JOIN FETCH u.truck t JOIN FETCH t.drivers WHERE u.driver_id=:driver_id")
                    .setParameter("driver_id", driver_id)
                    .getSingleResult();
        }catch (NoResultException e){
            return (Driver) getEntityManager()
                    .createQuery("SELECT u FROM Driver u  WHERE u.driver_id=:driver_id")
                    .setParameter("driver_id", driver_id)
                    .getSingleResult();
        }

    }

    @Override
    public int getLastId() {
        return (int) getEntityManager()
                .createQuery("SELECT COALESCE(MAX(driver_id),0) FROM Driver")
                .getSingleResult();
    }

    @Override
    public List<Driver> findSuitableDrivers(int distance, int avgSpeed, int shift, int mounth, int utoday, Truck truck) {

        return getEntityManager()
                .createQuery("SELECT u FROM Driver u WHERE " +
                        "NOT(((:mounth-u.worked_time)/:shift<=:utoday) " +
                        "AND (:distance/(:avgSpeed*:shift)>=(:mounth-u.worked_time)/:shift)) " +
                        "AND (u.city.city_id =:city_id)" +
                        "AND (u.user.enabled=true)" +
                        "AND (u.truck is null)"+
                        "AND (u.status='REST')")
                .setParameter("distance",(double) distance)
                .setParameter("avgSpeed",(double) avgSpeed)
                .setParameter("shift", shift)
                .setParameter("mounth", mounth)
                .setParameter("utoday", utoday)
                .setParameter("city_id", truck.getCity().getCity_id())
                .getResultList();

    }

    @Override
    public void setWorktimeForAll(int time, Date date) {
        getEntityManager()
                .createQuery("UPDATE Driver d SET d.worked_time=:time, d.start=:date" +
                        " WHERE d.start IS NOT NULL")
                .setParameter("time", time)
                .setParameter("date", date).executeUpdate();
        getEntityManager()
                .createQuery("UPDATE Driver SET worked_time=:time")
                .setParameter("time",time).executeUpdate();

    }

    @Override
    public List<Driver> findAll() {
        return getEntityManager()
                .createQuery("SELECT u FROM Driver u WHERE u.user.enabled = true ")
                .getResultList();
    }


}
