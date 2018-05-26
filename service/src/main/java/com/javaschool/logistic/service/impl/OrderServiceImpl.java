package com.javaschool.logistic.service.impl;

import com.javaschool.logistic.dao.api.OrderDao;
import com.javaschool.logistic.dao.api.TruckDao;
import com.javaschool.logistic.exception.ServiceException;
import com.javaschool.logistic.model.Cargo;
import com.javaschool.logistic.model.Driver;
import com.javaschool.logistic.model.Order;
import com.javaschool.logistic.model.Truck;
import com.javaschool.logistic.models.Waypoint;
import com.javaschool.logistic.service.api.OrderService;
import com.javaschool.logistic.service.api.OrderWaypointService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = Logger.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderDao orderDao;


    @Autowired
    private OrderWaypointService orderWaypointService;

    @Override
    public List<Order> findAllOrders() {
        return orderDao.findAll();
    }

    @Override
    public void createOrder(List<Waypoint> waypointList, Order order) throws ServiceException{
        try {
            orderDao.create(order);
            orderWaypointService.createWaypoints(waypointList,order);
            LOGGER.info("Order "+order+" has been created");
        }catch (ServiceException e){
            throw new ServiceException("Unexpected exception",e);
        }

    }

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
            LOGGER.info("Order "+order+" has been completed");
            return true;
        }
        return false;
    }

    @Override
    public Order findById(int order_id) {
        return orderDao.findById(order_id);
    }
}
