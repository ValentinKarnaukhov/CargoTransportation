package com.javaschool.logistic.service.impl;

import com.javaschool.logistic.dao.api.CargoDao;
import com.javaschool.logistic.exception.ServiceEmptyResultException;
import com.javaschool.logistic.exception.ServiceException;
import com.javaschool.logistic.exceptions.DaoEmptyResultException;
import com.javaschool.logistic.exceptions.DaoException;
import com.javaschool.logistic.model.Cargo;
import com.javaschool.logistic.service.api.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CargoServiceImpl implements CargoService {

    @Autowired
    private CargoDao cargoDao;


    @Override
    public List<Cargo> findByOrderId(int order_id) throws ServiceException {
        try {
            return cargoDao.findByOrderId(order_id);
        }catch (DaoEmptyResultException e){
            throw new ServiceEmptyResultException("Cargoes not found");
        }catch (DaoException e){
            throw new ServiceException("Unexpected exception",e);
        }

    }
}
