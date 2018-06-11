package com.javaschool.logistic.service.impl;

import com.javaschool.logistic.dao.api.DriverDao;
import com.javaschool.logistic.dao.api.OrderDao;
import com.javaschool.logistic.dao.api.TruckDao;
import com.javaschool.logistic.model.*;
import com.javaschool.logistic.models.JsonResponse;
import com.javaschool.logistic.service.api.ScoreboardService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ScoreboardServiceImplTest {

    @Mock
    private OrderDao orderDao;

    @Mock
    private TruckDao truckDao;

    @Mock
    private DriverDao driverDao;

    @InjectMocks
    private ScoreboardService scoreboardService = new ScoreboardServiceImpl();


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    private List<Order> getOrders(){

        List<Order> orderList = new LinkedList<>();
        List<OrderWaypoint> orderWaypoints = new LinkedList<>();
        OrderWaypoint orderWaypoint = new OrderWaypoint();
        orderWaypoint.setCity(new City());
        Order order = new Order();
        OrderHistory orderHistory = new OrderHistory();
        orderHistory.setTruck(new Truck());
        orderHistory.setDrivers(getDrivers());
        order.setOrderHistory(orderHistory);
        orderWaypoints.add(orderWaypoint);
        orderWaypoints.add(orderWaypoint);
        order.setOrderWaypoints(orderWaypoints);
        orderList.add(order);
        orderList.add(order);

        return orderList;

    }

    private List<Driver> getDrivers(){

        List<Driver> drivers = new LinkedList<>();
        drivers.add(new Driver());
        drivers.add(new Driver());

        return drivers;
    }


    @Test
    public void getResponse() {

        when(orderDao.findLastTen()).thenReturn(getOrders());

        JsonResponse jsonResponse = scoreboardService.getResponse();

        assertEquals(jsonResponse.getRows().get(0).getDrivers().get(0),"null - null null");
        assertNotNull(jsonResponse.getRows());

    }
}