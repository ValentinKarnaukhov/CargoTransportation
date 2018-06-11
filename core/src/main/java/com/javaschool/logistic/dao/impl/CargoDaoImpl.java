package com.javaschool.logistic.dao.impl;

import com.javaschool.logistic.dao.api.CargoDao;
import com.javaschool.logistic.model.Cargo;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class CargoDaoImpl extends GenericDaoImpl<Cargo> implements CargoDao {

    @Override
    public int getLastId(){
        return (int) getEntityManager()
                .createQuery("SELECT MAX(cargo_id) FROM Cargo ")
                .getSingleResult();

    }

    @Override
    public List findByOrderId(int order_id){
        List<Cargo> cargoes;

           cargoes = getEntityManager()
                    .createQuery("SELECT DISTINCT u FROM Cargo u JOIN FETCH u.orderWaypoint WHERE u.order.order_id=:order_id")
                    .setParameter("order_id",order_id)
                    .getResultList();
           return cargoes;
    }
}
