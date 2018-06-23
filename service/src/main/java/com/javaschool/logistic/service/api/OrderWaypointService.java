package com.javaschool.logistic.service.api;


import com.javaschool.logistic.models.Order;
import com.javaschool.logistic.models.OrderWaypoint;
import com.javaschool.logistic.models.Waypoint;

import java.util.List;

public interface OrderWaypointService {

    void createWaypoints(List<Waypoint> waypointList, Order order);

    List<OrderWaypoint> findByOrderId(int order_id);

    OrderWaypoint findById(int point_id);

    boolean updatePoint(OrderWaypoint point);

    List<OrderWaypoint> findByOrderIdLoad(int order_id);

    OrderWaypoint findUnloadByCargoId(int cargo_id);

}
