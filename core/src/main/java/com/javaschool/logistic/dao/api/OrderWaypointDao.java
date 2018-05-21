package com.javaschool.logistic.dao.api;

import com.javaschool.logistic.model.OrderWaypoint;

import java.util.List;

public interface OrderWaypointDao extends GenericDao<OrderWaypoint> {

    List<OrderWaypoint> findByOrderId(int order_id);

    OrderWaypoint findById(int point_id);

}
