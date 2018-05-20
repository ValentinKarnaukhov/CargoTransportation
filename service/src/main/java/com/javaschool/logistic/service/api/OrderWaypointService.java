package com.javaschool.logistic.service.api;

import com.javaschool.logistic.model.Order;
import com.javaschool.logistic.model.OrderWaypoint;
import com.javaschool.logistic.models.Waypoint;

import java.util.List;

public interface OrderWaypointService {



    void createWaypoints(List<Waypoint> waypointList, Order order);

    List<OrderWaypoint> findByOrderId(int order_id);

    OrderWaypoint findById(int point_id);

    void updatePoint(OrderWaypoint point);

}
