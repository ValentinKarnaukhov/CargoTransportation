package com.javaschool.logistic.service.impl;

import com.javaschool.logistic.dao.api.OrderDao;
import com.javaschool.logistic.model.Driver;
import com.javaschool.logistic.model.Order;
import com.javaschool.logistic.models.Waypoint;
import com.javaschool.logistic.service.api.OrderService;
import com.javaschool.logistic.service.api.OrderWaypointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDao orderDao;

    @Autowired
    OrderWaypointService orderWaypointService;

    @Override
    public List<Order> findAllOrders() {
        return orderDao.findAll();
    }

    @Override
    public void createOrder(List<Waypoint> waypointList, Order order) {
        orderDao.create(order);

        orderWaypointService.createWaypoints(waypointList,order);
    }
}
