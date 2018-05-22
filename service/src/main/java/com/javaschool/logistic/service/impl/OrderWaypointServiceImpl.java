package com.javaschool.logistic.service.impl;

import com.javaschool.logistic.dao.api.CargoDao;
import com.javaschool.logistic.dao.api.OrderWaypointDao;
import com.javaschool.logistic.exception.ServiceException;
import com.javaschool.logistic.exceptions.DaoException;
import com.javaschool.logistic.model.Cargo;
import com.javaschool.logistic.model.Order;
import com.javaschool.logistic.model.OrderWaypoint;
import com.javaschool.logistic.models.Waypoint;
import com.javaschool.logistic.service.api.OrderService;
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
    private OrderWaypointDao orderWaypointDao;

    @Autowired
    private OrderService orderService;

    @Override
    public void createWaypoints(List<Waypoint> waypointList, Order order)throws ServiceException {
        OrderWaypoint orderWaypoint;
        for(Waypoint waypoint:waypointList){
            Cargo cargo = waypoint.getCargo();
            cargo.setOrder(order);
            try {
                cargo.setNumber("c"+(cargoDao.getLastId()+1));
            }catch (DaoException e){
                throw  new ServiceException("Unexpected exception",e);
            }
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
    public List<OrderWaypoint> findByOrderId(int order_id) {
        return orderWaypointDao.findByOrderId(order_id);
    }

    @Override
    public OrderWaypoint findById(int point_id) {
        return orderWaypointDao.findById(point_id);
    }


    //TODO-change status only when both equal DONE
    @Override
    public void updatePoint(OrderWaypoint point) {
        if(point.getOperation().equals(OrderWaypoint.Operation.LOADING)
                &&point.getStatus().equals(OrderWaypoint.Status.DONE)){
            Cargo cargo = point.getCargo();
            cargo.setStatus(Cargo.Status.SHIPPED);
            cargoDao.update(cargo);
        }

        if(point.getOperation().equals(OrderWaypoint.Operation.UNLOADING)
                &&point.getStatus().equals(OrderWaypoint.Status.DONE)){
            Cargo cargo = point.getCargo();
            cargo.setStatus(Cargo.Status.DONE);
            cargoDao.update(cargo);
            orderService.checkCompleted(cargo.getOrder().getOrder_id());
        }

        orderWaypointDao.update(point);
    }
}
