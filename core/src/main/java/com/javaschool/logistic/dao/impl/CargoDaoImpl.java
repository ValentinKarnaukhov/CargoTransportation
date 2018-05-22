package com.javaschool.logistic.dao.impl;

import com.javaschool.logistic.dao.api.CargoDao;
import com.javaschool.logistic.exceptions.DaoEmptyResultException;
import com.javaschool.logistic.exceptions.DaoException;
import com.javaschool.logistic.model.Cargo;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class CargoDaoImpl extends GenericDaoImpl<Cargo> implements CargoDao {

    @Override
    public int getLastId() throws DaoException{
        int id;
        try {
            id=(int) getEntityManager()
                    .createQuery("SELECT MAX(u.cargo_id) FROM Cargo u")
                    .getSingleResult();
        }catch (NullPointerException e){
            return 0;
        }catch (Exception e){
            throw new DaoException("Unexpected exception", e);
        }
        return id;
    }

    @Override
    public List findByOrderId(int order_id) throws DaoEmptyResultException, DaoException{
        List<Cargo> cargoes;
        try {
           cargoes = getEntityManager()
                    .createQuery("SELECT DISTINCT u FROM Cargo u JOIN FETCH u.orderWaypoint WHERE u.order.order_id=:order_id")
                    .setParameter("order_id",order_id)
                    .getResultList();
            if(cargoes.isEmpty()){
                throw new DaoEmptyResultException("Cargoes not found");
            }
        }catch (Exception e){
            throw new DaoException("Unexpected exception", e);
        }


        return cargoes;
    }
}
