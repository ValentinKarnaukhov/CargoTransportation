package com.javaschool.logistic.dao.impl;

import com.javaschool.logistic.dao.api.CargoDao;
import com.javaschool.logistic.exeption.DaoException;
import com.javaschool.logistic.model.Cargo;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class CargoDaoImpl extends GenericDaoImpl<Cargo> implements CargoDao {

    private static final Logger LOGGER = Logger.getLogger(CargoDaoImpl.class);

    @Override
    public int getLastId() throws DaoException {
        try {
            int id;
            try {
                id=(int) getEntityManager()
                        .createQuery("SELECT MAX(u.cargo_id) FROM Cargo u")
                        .getSingleResult();
            }catch (NullPointerException e){
                return 0;
            }
            return id;
        } catch (Exception e){
            LOGGER.error("Unexpected database exception",e);
            throw new DaoException(e);
        }

    }

    @Override
    public List<Cargo> findByOrderId(int order_id) {
        return getEntityManager()
                .createQuery("SELECT DISTINCT u FROM Cargo u JOIN FETCH u.orderWaypoint WHERE u.order.order_id=:order_id")
                .setParameter("order_id",order_id)
                .getResultList();
    }
}
