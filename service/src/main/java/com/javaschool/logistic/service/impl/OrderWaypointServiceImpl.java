package com.javaschool.logistic.service.impl;

import com.javaschool.logistic.dao.api.CargoDao;
import com.javaschool.logistic.dao.api.OrderWaypointDao;
import com.javaschool.logistic.dao.api.TruckDao;

import com.javaschool.logistic.model.*;
import com.javaschool.logistic.models.Waypoint;
import com.javaschool.logistic.service.api.OrderService;
import com.javaschool.logistic.service.api.OrderWaypointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;


@Service
public class OrderWaypointServiceImpl implements OrderWaypointService {

    @Autowired
    private CargoDao cargoDao;

    @Autowired
    private OrderWaypointDao orderWaypointDao;

    @Autowired
    private OrderService orderService;

    @Autowired
    private TruckDao truckDao;


    @Override
    @Transactional
    public void createWaypoints(List<Waypoint> waypointList, Order order) {
        OrderWaypoint orderWaypoint;
        for(Waypoint waypoint:waypointList){
            Cargo cargo = waypoint.getCargo();
            cargo.setOrder(order);
            cargo.setNumber("c"+(cargoDao.getLastId()+1));
            orderWaypoint = new OrderWaypoint();
            orderWaypoint.setCity(waypoint.getLoadingCity());
            orderWaypoint.setOrder(order);
            orderWaypoint.setCargo(cargo);
            cargoDao.create(waypoint.getCargo());
            orderWaypoint.setOperation(OrderWaypoint.Operation.LOADING);
            orderWaypointDao.create(orderWaypoint);
            orderWaypoint = new OrderWaypoint();
            orderWaypoint.setCity(waypoint.getUnloadingCity());
            orderWaypoint.setOrder(order);
            orderWaypoint.setCargo(cargo);
            orderWaypoint.setOperation(OrderWaypoint.Operation.UNLOADING);
            orderWaypointDao.create(orderWaypoint);

        }

    }


    @Override
    @Transactional
    public List<OrderWaypoint> findByOrderId(int order_id) {
        return orderWaypointDao.findByOrderId(order_id);
    }


    @Override
    @Transactional
    public OrderWaypoint findById(int point_id) {
        return orderWaypointDao.findById(point_id);
    }


    @Override
    @Transactional
    public boolean updatePoint(OrderWaypoint point) {
        Truck truck = point.getOrder().getTruck();
        if(point.getOperation().equals(OrderWaypoint.Operation.LOADING)
                &&point.getStatus().equals(OrderWaypoint.Status.DONE)){
            Cargo cargo = point.getCargo();
            cargo.setStatus(Cargo.Status.SHIPPED);
            cargoDao.update(cargo);
            truck.setCity(point.getCity());
            for(Driver driver:truck.getDrivers()){
                driver.setCity(point.getCity());
            }
            truckDao.update(truck);
        }

        if(point.getOperation().equals(OrderWaypoint.Operation.UNLOADING)
                &&point.getStatus().equals(OrderWaypoint.Status.DONE)){
            Cargo cargo = point.getCargo();
            cargo.setStatus(Cargo.Status.DONE);
            cargoDao.update(cargo);
            truck.setCity(point.getCity());
            for(Driver driver:truck.getDrivers()){
                driver.setCity(point.getCity());
            }
            truckDao.update(truck);

        }
        orderWaypointDao.update(point);
        return orderService.checkCompleted(point.getCargo().getOrder().getOrder_id());
    }


    @Override
    @Transactional
    public List<OrderWaypoint> findByOrderIdLoad(int order_id) {
        List<OrderWaypoint> waypoints = new LinkedList<>();
        for(OrderWaypoint p:orderWaypointDao.findByOrderId(order_id)){
            if(p.getStatus().equals(OrderWaypoint.Status.PROGRESS)
                    &&p.getOperation().equals(OrderWaypoint.Operation.LOADING)){
                waypoints.add(p);
            }
            if(p.getStatus().equals(OrderWaypoint.Status.PROGRESS)
                    &&p.getOperation().equals(OrderWaypoint.Operation.UNLOADING)
                    &&p.getCargo().getOrderWaypoint().get(0).getStatus().equals(OrderWaypoint.Status.DONE)){
                waypoints.add(p);
            }
        }
        return waypoints;
    }


    @Override
    @Transactional
    public OrderWaypoint findUnloadByCargoId(int cargo_id) {
        return orderWaypointDao.findUnloadByCargoId(cargo_id);
    }
}
