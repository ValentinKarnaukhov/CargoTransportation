package com.javaschool.logistic.dao.impl;

import com.javaschool.logistic.dao.api.OrderDao;
import com.javaschool.logistic.model.Order;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class OrderDaoImpl extends GenericDaoImpl<Order> implements OrderDao {

    private static final Logger LOGGER = Logger.getLogger(OrderDaoImpl.class);

    @Override
    public Order findById(int order_id) {
        try {
            return (Order) getEntityManager()
                    .createQuery("SELECT u FROM Order u JOIN FETCH u.truck t JOIN FETCH t.drivers WHERE u.order_id=:id")
                    .setParameter("id",order_id)
                    .getSingleResult();
        }catch (NoResultException e){
            LOGGER.error("Order doesn't exist", e);
            return null;
        }
    }

    @Override
    public List<Order> findLastTen() {
        return getEntityManager()
                .createQuery("SELECT o FROM Order o JOIN FETCH o.orderHistory ORDER BY o.order_id DESC")
                .setMaxResults(10)
                .getResultList();
    }


}
