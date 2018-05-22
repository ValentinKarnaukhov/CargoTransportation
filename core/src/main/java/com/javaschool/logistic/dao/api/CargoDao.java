package com.javaschool.logistic.dao.api;


import com.javaschool.logistic.exceptions.DaoException;
import com.javaschool.logistic.model.Cargo;

import java.util.List;

public interface CargoDao extends GenericDao<Cargo> {

    int getLastId() throws DaoException;

    List findByOrderId(int order_id) throws DaoException;


}
