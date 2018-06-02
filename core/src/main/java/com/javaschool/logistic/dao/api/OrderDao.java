package com.javaschool.logistic.dao.api;


import com.javaschool.logistic.model.Order;

import java.util.List;

public interface OrderDao extends GenericDao<Order> {

    Order findById(int order_id);

    List<Order> findLastTen();

}
