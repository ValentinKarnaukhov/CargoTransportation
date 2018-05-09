package com.javaschool.logistic.service.api;

import com.javaschool.logistic.model.Order;

import java.util.List;

public interface OrderService {

    List<Order> findAllOrders();
}
