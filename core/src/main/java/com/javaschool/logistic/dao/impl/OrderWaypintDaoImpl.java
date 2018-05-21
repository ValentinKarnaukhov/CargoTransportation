package com.javaschool.logistic.dao.impl;

import com.javaschool.logistic.dao.api.OrderWaypointDao;
import com.javaschool.logistic.model.OrderWaypoint;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;


@Repository
public class OrderWaypintDaoImpl extends GenericDaoImpl<OrderWaypoint> implements OrderWaypointDao {

    private static final Logger LOGGER = Logger.getLogger(OrderWaypintDaoImpl.class);


    @Override
    public List<OrderWaypoint> findByOrderId(int order_id) {
        return getEntityManager()
                .createQuery("SELECT u FROM OrderWaypoint u WHERE u.order.order_id=:order_id")
                .setParameter("order_id",order_id)
                .getResultList();
    }

    @Override
    public OrderWaypoint findById(int point_id) {
        try {
            return (OrderWaypoint) getEntityManager()
                    .createQuery("SELECT u FROM OrderWaypoint u WHERE u.order_waypoint_id=:point")
                    .setParameter("point",point_id)
                    .getSingleResult();
        }catch (NoResultException e){
            LOGGER.error("OrderWaypoint doesn't exist", e);
            return null;
        }
    }
}
