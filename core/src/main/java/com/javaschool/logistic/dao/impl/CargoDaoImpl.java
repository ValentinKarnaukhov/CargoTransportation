package com.javaschool.logistic.dao.impl;

import com.javaschool.logistic.dao.api.CargoDao;
import com.javaschool.logistic.model.Cargo;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;


@Repository
public class CargoDaoImpl extends GenericDaoImpl<Cargo> implements CargoDao {

    @Override
    public int getLastId(){
        int id;
        try {
            id=(int) getEntityManager()
                    .createQuery("SELECT MAX(u.cargo_id) FROM Cargo u")
                    .getSingleResult();
        }catch (NullPointerException e){
            return 0;
        }
        return id;
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
