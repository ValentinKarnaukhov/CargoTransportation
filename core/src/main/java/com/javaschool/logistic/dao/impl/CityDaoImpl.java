package com.javaschool.logistic.dao.impl;

import com.javaschool.logistic.dao.api.CityDao;
import com.javaschool.logistic.models.City;
import com.javaschool.logistic.models.Truck;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;


@Repository
public class CityDaoImpl extends GenericDaoImpl<City> implements CityDao {

    private static final Logger LOGGER = Logger.getLogger(CityDaoImpl.class);

    @Override
    public List<Truck> findAllTrucks(String cityName) {
        return getEntityManager()
                .createQuery("SELECT u FROM Truck u WHERE u.city.name LIKE :cityName")
                .setParameter("cityName",cityName).getResultList();
    }

    @Override
    public City findById(int city_id) {
        try {
            return (City) getEntityManager()
                    .createQuery("SELECT u FROM City u WHERE u.city_id=:city_id")
                    .setParameter("city_id",city_id)
                    .getSingleResult();
        }catch (NoResultException e){
            LOGGER.error("City doesn't exist", e);
            return null;
        }

    }

}
