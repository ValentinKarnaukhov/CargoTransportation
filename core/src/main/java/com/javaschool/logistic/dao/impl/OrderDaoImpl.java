package com.javaschool.logistic.dao.impl;

import com.javaschool.logistic.dao.api.OrderDao;
import com.javaschool.logistic.model.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

@Repository
public class OrderDaoImpl extends GenericDaoImpl<Order> implements OrderDao {


    @Override
    public Order findById(int order_id) {
        return (Order) getEntityManager()
                    .createQuery("SELECT u FROM Order u JOIN FETCH u.truck t JOIN FETCH t.drivers WHERE u.order_id=:id")
                    .setParameter("id",order_id)
                    .getSingleResult();


    }
}
