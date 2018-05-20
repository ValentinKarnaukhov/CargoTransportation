package com.javaschool.logistic.dao.api;

import com.javaschool.logistic.exeption.DaoException;
import com.javaschool.logistic.model.OrderWaypoint;

import java.util.List;

public interface OrderWaypointDao extends GenericDao<OrderWaypoint> {

    List<OrderWaypoint> findByOrderId(int order_id) throws DaoException;

    OrderWaypoint findById(int point_id) throws DaoException;

}
