package com.javaschool.logistic.service.impl;

import com.javaschool.logistic.dao.api.OrderDao;
import com.javaschool.logistic.model.Order;
import com.javaschool.logistic.service.api.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDao orderDao;

    @Override
    public List<Order> findAllOrders() {
        return orderDao.findAll();
    }
}
