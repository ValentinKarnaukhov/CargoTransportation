package com.javaschool.logistic.dao.api;

import com.javaschool.logistic.models.OrderWaypoint;

import java.util.List;

public interface OrderWaypointDao extends GenericDao<OrderWaypoint> {

    List<OrderWaypoint> findByOrderId(int order_id);

    OrderWaypoint findById(int point_id);

    List<OrderWaypoint> findByOrderIdLoad(int order_id);

    OrderWaypoint findUnloadByCargoId(int cargo_id);

}
