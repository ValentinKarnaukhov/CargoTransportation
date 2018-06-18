package com.javaschool.logistic.dao.impl;

import com.javaschool.logistic.dao.api.ExternalDao;
import com.javaschool.logistic.model.External;
import org.springframework.stereotype.Repository;

@Repository
public class ExternalDaoImpl extends GenericDaoImpl<External> implements ExternalDao {


    @Override
    public void deleteById(int id) {
        getEntityManager()
                .createQuery("DELETE FROM External WHERE external_id=:id")
                .setParameter("id",id)
                .executeUpdate();
    }
}
