package com.javaschool.logistic.service.impl;

import com.javaschool.logistic.dao.api.ExternalDao;
import com.javaschool.logistic.dao.api.OrderDao;
import com.javaschool.logistic.dao.api.OrderHistoryDao;

import com.javaschool.logistic.model.*;
import com.javaschool.logistic.models.Waypoint;
import com.javaschool.logistic.service.api.OrderService;
import com.javaschool.logistic.service.api.OrderWaypointService;
import com.javaschool.logistic.utils.Mapper;
import org.apache.log4j.Logger;
import org.springframework.amqp.AmqpConnectException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = Logger.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderWaypointService orderWaypointService;

    @Autowired
    private OrderHistoryDao orderHistoryDao;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private ExternalDao externalDao;

    @Transactional
    @Override
    public List<Order> findAllOrders() {
        return orderDao.findAll();
    }

    @Transactional
    @Override
    public void createOrder(List<Waypoint> waypointList, Order order){

        for(Waypoint waypoint:waypointList){
            if(waypoint.isExternal()){
                externalDao.deleteById(waypoint.getExternal_id());
            }
        }

        orderDao.create(order);
        orderWaypointService.createWaypoints(waypointList,order);
        orderHistoryDao.create(new OrderHistory(order,order.getTruck(),order.getTruck().getDrivers()));
        try {
            amqpTemplate.convertAndSend("infoQueue", "update");
        }catch (AmqpConnectException e){
            LOGGER.warn("Queue server not available", e);
        }
        LOGGER.info("Order "+order+" has been created");


    }

    @Transactional
    @Override
    public boolean checkCompleted(int order_id){
        Order order = orderDao.findById(order_id);
        int amountDone = 0;
        for(Cargo cargo:order.getCargoes()){
            if(cargo.getStatus().equals(Cargo.Status.DONE)){
                    amountDone+=1;
            }
        }
        if(order.getCargoes().size()==amountDone){
            order.getTruck().setOrder(null);
            for(Driver driver:order.getTruck().getDrivers()){
                driver.setTruck(null);
            }
            order.setComplete(true);
            orderDao.update(order);
            try {
                amqpTemplate.convertAndSend("infoQueue", "update");
            }catch (AmqpConnectException e){
                LOGGER.warn("Queue server not available", e);
            }
            LOGGER.info("Order "+order+" has been completed");
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public Order findById(int order_id) {
        return orderDao.findById(order_id);
    }
}
