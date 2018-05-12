package com.javaschool.logistic.dao.impl;

import com.javaschool.logistic.dao.api.CityDao;
import com.javaschool.logistic.model.City;
import com.javaschool.logistic.model.Truck;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Valentin
 */

@Repository
public class CityDaoImpl extends GenericDaoImpl<City> implements CityDao {

    @Override
    public List<Truck> findAllTrucks(String cityName) {
        return getEntityManager()
                .createQuery("SELECT u FROM Truck u WHERE u.city.name LIKE :cityName")
                .setParameter("cityName",cityName).getResultList();
    }

    @Override
    public City findById(int city_id) {
        return (City) getEntityManager()
                .createQuery("SELECT u FROM City u WHERE u.city_id=:city_id")
                .setParameter("city_id",city_id)
                .getSingleResult();
    }

}
