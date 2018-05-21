package com.javaschool.logistic.service.api;


import com.javaschool.logistic.exception.DatabaseException;
import com.javaschool.logistic.model.Cargo;

import java.util.List;

public interface CargoService {

    List<Cargo> findByOrderId(int order_id);



}
