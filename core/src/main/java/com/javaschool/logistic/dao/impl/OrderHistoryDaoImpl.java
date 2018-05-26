package com.javaschool.logistic.dao.impl;

import com.javaschool.logistic.dao.api.OrderHistoryDao;
import com.javaschool.logistic.model.OrderHistory;
import org.springframework.stereotype.Repository;

@Repository
public class OrderHistoryDaoImpl extends GenericDaoImpl<OrderHistory> implements OrderHistoryDao {
}
