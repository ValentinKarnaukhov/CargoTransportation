package com.javaschool.logistic.dao.impl;

import com.javaschool.logistic.dao.api.OrderDao;
import com.javaschool.logistic.model.Order;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl extends GenericDaoImpl<Order> implements OrderDao {
}
