package com.javaschool.logistic.service.impl;

import com.javaschool.logistic.dao.api.CargoDao;
import com.javaschool.logistic.dao.api.OrderWaypointDao;
import com.javaschool.logistic.model.Cargo;
import com.javaschool.logistic.model.Order;
import com.javaschool.logistic.model.OrderWaypoint;
import com.javaschool.logistic.models.Waypoint;
import com.javaschool.logistic.service.api.OrderWaypointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class OrderWaypointServiceImpl implements OrderWaypointService {

    @Autowired
    private CargoDao cargoDao;


    @Autowired
    OrderWaypointDao orderWaypointDao;

    @Override
    public void createWaypoints(List<Waypoint> waypointList, Order order) {

        for(Waypoint waypoint:waypointList){
            Cargo cargo = waypoint.getCargo();
            cargo.setOrder(order);
            cargo.setNumber("c"+order.getOrder_id());
            OrderWaypoint orderWaypoint = new OrderWaypoint();
            orderWaypoint.setCity(waypoint.getLoadingCity());
            orderWaypoint.setOrder(order);
            orderWaypoint.setCargo(cargo);
            cargoDao.create(waypoint.getCargo());
            orderWaypoint.setOperation(OrderWaypoint.Operation.LOADING);
            orderWaypointDao.create(orderWaypoint);
            orderWaypoint.setOperation(OrderWaypoint.Operation.UNLOADING);
            orderWaypointDao.create(orderWaypoint);
        }

    }
}
