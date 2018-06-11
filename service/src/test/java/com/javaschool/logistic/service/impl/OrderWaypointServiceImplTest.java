package com.javaschool.logistic.service.impl;

import com.javaschool.logistic.dao.api.CargoDao;
import com.javaschool.logistic.dao.api.OrderWaypointDao;
import com.javaschool.logistic.dao.api.TruckDao;
import com.javaschool.logistic.model.*;
import com.javaschool.logistic.models.Waypoint;
import com.javaschool.logistic.service.api.OrderService;
import com.javaschool.logistic.service.api.OrderWaypointService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static com.javaschool.logistic.model.Cargo.Status.DONE;
import static com.javaschool.logistic.model.Cargo.Status.SHIPPED;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class OrderWaypointServiceImplTest {


    @Mock
    private CargoDao cargoDao;

    @Mock
    private OrderWaypointDao orderWaypointDao;

    @Mock
    private TruckDao truckDao;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderWaypointService orderWaypointService = new OrderWaypointServiceImpl();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }


    private List<Waypoint> getWaypoints(){

        Waypoint waypoint = new Waypoint();
        waypoint.setCargo(new Cargo());
        waypoint.setLoadingCity(new City());
        waypoint.setUnloadingCity(new City());

        List<Waypoint> waypoints = new LinkedList<>();
        waypoints.add(waypoint);
        waypoints.add(waypoint);

        return waypoints;
    }


     private List<Driver> getDrivers(){
        List<Driver> drivers = new LinkedList<>();

        Driver driver = new Driver();

        drivers.add(driver);
        drivers.add(driver);

        return drivers;
     }

     private Cargo getCargo(){

        Cargo cargo = new Cargo();
        OrderWaypoint orderWaypoint = new OrderWaypoint();
        List<OrderWaypoint> waypoints = new LinkedList<>();
        orderWaypoint.setStatus(OrderWaypoint.Status.DONE);
        waypoints.add(orderWaypoint);
        waypoints.add(orderWaypoint);
        cargo.setOrderWaypoint(waypoints);

        return cargo;
     }

     private List<OrderWaypoint> getOrderWaypoints(){

        List<OrderWaypoint> waypoints = new LinkedList<>();

        OrderWaypoint orderWaypoint = new OrderWaypoint();
        orderWaypoint.setStatus(OrderWaypoint.Status.PROGRESS);
        orderWaypoint.setOperation(OrderWaypoint.Operation.LOADING);
        orderWaypoint.setCargo(getCargo());
        waypoints.add(orderWaypoint);

        orderWaypoint=new OrderWaypoint();
        orderWaypoint.setStatus(OrderWaypoint.Status.PROGRESS);
        orderWaypoint.setOperation(OrderWaypoint.Operation.UNLOADING);
        orderWaypoint.setCargo(getCargo());
        waypoints.add(orderWaypoint);

        orderWaypoint=new OrderWaypoint();
        orderWaypoint.setOperation(OrderWaypoint.Operation.UNLOADING);
        orderWaypoint.setStatus(OrderWaypoint.Status.DONE);
        orderWaypoint.setCargo(getCargo());
        waypoints.add(orderWaypoint);

        return waypoints;
     }




    @Test
    public void createWaypoints() {

        when(cargoDao.getLastId()).thenReturn(0);
        doNothing().when(cargoDao).create(any(Cargo.class));
        doNothing().when(orderWaypointDao).create(any(OrderWaypoint.class));

        List<Waypoint> waypoints = getWaypoints();

        orderWaypointService.createWaypoints(waypoints,new Order());

        assertEquals(waypoints.get(0).getCargo().getNumber(),"c1");

    }

    @Test
    public void updatePoint() {


        OrderWaypoint waypoint = new OrderWaypoint();
        waypoint.setOperation(OrderWaypoint.Operation.LOADING);
        waypoint.setStatus(OrderWaypoint.Status.DONE);
        waypoint.setCargo(new Cargo());
        waypoint.setOrder(new Order());
        waypoint.getOrder().setTruck(new Truck());
        waypoint.getOrder().getTruck().setDrivers(getDrivers());
        waypoint.getCargo().setOrder(new Order());
        waypoint.setCity(new City());

        doNothing().when(cargoDao).update(any(Cargo.class));
        doNothing().when(truckDao).update(any(Truck.class));
        doNothing().when(orderWaypointDao).update(any(OrderWaypoint.class));
        when(orderService.checkCompleted(waypoint.getCargo().getOrder().getOrder_id())).thenReturn(true);

        orderWaypointService.updatePoint(waypoint);

        assertEquals(waypoint.getCargo().getStatus(),SHIPPED);
        assertNotNull(waypoint.getOrder().getTruck());
        assertNotNull(waypoint.getOrder().getTruck().getDrivers().get(0).getCity());

        waypoint.setOperation(OrderWaypoint.Operation.UNLOADING);
        waypoint.setStatus(OrderWaypoint.Status.DONE);

        orderWaypointService.updatePoint(waypoint);

        assertEquals(waypoint.getCargo().getStatus(),DONE);

    }

    @Test
    public void findByOrderIdLoad() {

        when(orderWaypointDao.findByOrderId(anyInt())).thenReturn(getOrderWaypoints());

        List<OrderWaypoint> waypoints= orderWaypointService.findByOrderIdLoad(anyInt());

        assertEquals(waypoints.size(),2);
    }

    @Test
    public void findUnloadByCargoId() {

        when(orderWaypointDao.findUnloadByCargoId(anyInt())).thenReturn(new OrderWaypoint());
        orderWaypointService.findUnloadByCargoId(anyInt());
        verify(orderWaypointDao).findUnloadByCargoId(anyInt());

    }
}