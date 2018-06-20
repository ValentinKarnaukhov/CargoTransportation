package com.javaschool.logistic.service.impl;

import com.javaschool.logistic.dao.api.CargoDao;
import com.javaschool.logistic.models.Cargo;
import com.javaschool.logistic.service.api.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class CargoServiceImpl implements CargoService {

    private CargoDao cargoDao;

    @Autowired
    public CargoServiceImpl(CargoDao cargoDao) {
        this.cargoDao = cargoDao;
    }

    public CargoServiceImpl() {
    }

    @Override
    @Transactional
    public List<Cargo> findByOrderId(int order_id){
            return cargoDao.findByOrderId(order_id);
    }
}
