package com.javaschool.logistic.service.impl;

import com.javaschool.logistic.dao.api.CargoDao;
import com.javaschool.logistic.dao.api.OrderWaypointDao;
import com.javaschool.logistic.dao.api.TruckDao;

import com.javaschool.logistic.model.*;
import com.javaschool.logistic.models.OutgoingMessage;
import com.javaschool.logistic.models.Waypoint;
import com.javaschool.logistic.service.api.ExternalService;
import com.javaschool.logistic.service.api.OrderService;
import com.javaschool.logistic.service.api.OrderWaypointService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;


@Service
public class OrderWaypointServiceImpl implements OrderWaypointService {

    private CargoDao cargoDao;

    private OrderWaypointDao orderWaypointDao;

    private OrderService orderService;

    private TruckDao truckDao;

    private RabbitTemplate rabbitTemplate;

    private ExternalService externalService;

    @Autowired
    public OrderWaypointServiceImpl(CargoDao cargoDao, OrderWaypointDao orderWaypointDao,
                                    OrderService orderService, TruckDao truckDao,
                                    RabbitTemplate rabbitTemplate, ExternalService externalService) {
        this.cargoDao = cargoDao;
        this.orderWaypointDao = orderWaypointDao;
        this.orderService = orderService;
        this.truckDao = truckDao;
        this.rabbitTemplate = rabbitTemplate;
        this.externalService = externalService;
    }

    public OrderWaypointServiceImpl() {
    }

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

            if(cargo.isExternal()){
                rabbitTemplate
                        .convertAndSend("answers",
                                new OutgoingMessage(externalService.getId(cargo.getName()),OutgoingMessage.Status.SHIPPED));
            }


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

            if(cargo.isExternal()){
                rabbitTemplate
                        .convertAndSend("answers",
                                new OutgoingMessage(externalService.getId(cargo.getName()),OutgoingMessage.Status.DELIVERED));
            }

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
