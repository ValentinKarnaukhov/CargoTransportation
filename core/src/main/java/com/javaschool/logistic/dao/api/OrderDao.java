package com.javaschool.logistic.dao.api;


import com.javaschool.logistic.model.Order;

public interface OrderDao extends GenericDao<Order> {

    Order findById(int order_id);

}
