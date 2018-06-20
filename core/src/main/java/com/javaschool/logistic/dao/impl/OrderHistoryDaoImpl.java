package com.javaschool.logistic.dao.impl;

import com.javaschool.logistic.dao.api.OrderHistoryDao;
import com.javaschool.logistic.models.OrderHistory;
import org.springframework.stereotype.Repository;

@Repository
public class OrderHistoryDaoImpl extends GenericDaoImpl<OrderHistory> implements OrderHistoryDao {
}
