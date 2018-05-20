package com.javaschool.logistic.dao.impl;

import com.javaschool.logistic.dao.api.OrderWaypointDao;
import com.javaschool.logistic.model.OrderWaypoint;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class OrderWaypintDaoImpl extends GenericDaoImpl<OrderWaypoint> implements OrderWaypointDao {

    @Override
    public List<OrderWaypoint> findByOrderId(int order_id) {
        return getEntityManager()
                .createQuery("SELECT u FROM OrderWaypoint u WHERE u.order.order_id=:order_id")
                .setParameter("order_id",order_id)
                .getResultList();
    }

    @Override
    public OrderWaypoint findById(int point_id) {
        return (OrderWaypoint) getEntityManager()
                .createQuery("SELECT u FROM OrderWaypoint u WHERE u.order_waypoint_id=:point")
                .setParameter("point",point_id)
                .getSingleResult();
    }
}
