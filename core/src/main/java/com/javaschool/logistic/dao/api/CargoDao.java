package com.javaschool.logistic.dao.api;

import com.javaschool.logistic.model.Cargo;

import java.util.List;

public interface CargoDao extends GenericDao<Cargo> {

    int getLastId();

    List findByOrderId(int order_id);


}
