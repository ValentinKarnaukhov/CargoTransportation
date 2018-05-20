package com.javaschool.logistic.service.impl;

import com.javaschool.logistic.dao.api.CargoDao;
import com.javaschool.logistic.model.Cargo;
import com.javaschool.logistic.service.api.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CargoServiceImpl implements CargoService {

    @Autowired
    CargoDao cargoDao;


    @Override
    public List<Cargo> findByOrderId(int order_id) {
        return cargoDao.findByOrderId(order_id);
    }
}
