package com.javaschool.logistic.service.api;


import com.javaschool.logistic.exception.ServiceEmptyResultException;
import com.javaschool.logistic.exception.ServiceException;
import com.javaschool.logistic.model.Cargo;

import java.rmi.server.ServerCloneException;
import java.util.List;

public interface CargoService {

    List<Cargo> findByOrderId(int order_id) throws ServiceException;



}
