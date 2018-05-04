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
    public List<Truck> getTrucks(String cityName) {
        return getEntityManager()
                .createQuery("SELECT u FROM Truck u WHERE u.city.name LIKE :cityName")
                .setParameter("cityName",cityName).getResultList();
    }
}
