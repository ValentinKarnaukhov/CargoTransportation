package com.javaschool.logistic.service.impl;

import com.javaschool.logistic.dao.api.TruckDao;
import com.javaschool.logistic.models.Truck;
import com.javaschool.logistic.service.api.TruckService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.core.AmqpTemplate;

import java.util.LinkedList;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class TruckServiceImplTest {

    @Mock
    private TruckDao truckDao;

    @Mock
    private AmqpTemplate amqpTemplate;

    @InjectMocks
    private TruckService truckService = new TruckServiceImpl();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    private Truck getTruck(){
        Truck truck = new Truck();
        truck.setReg_number("aa12345");
        return truck;
    }

    @Test
    public void findAllTrucks() {
        when(truckDao.findById(anyInt())).thenReturn(getTruck());
        truckService.findAllTrucks();
        verify(truckDao).findAll();
    }

    @Test
    public void createTruck() {
        doNothing().when(truckDao).create(any());
        doNothing().when(amqpTemplate).convertAndSend(any());
        truckService.createTruck(getTruck());
        verify(truckDao).create(any());
    }

    @Test
    public void deleteById() {
        doNothing().when(truckDao).delete(any());
        doNothing().when(amqpTemplate).convertAndSend(any());
        when(truckService.findById(anyInt())).thenReturn(getTruck());
        truckService.deleteById(anyInt());
        verify(truckDao).update(any());
    }

    @Test
    public void findById() {
        when(truckDao.findById(anyInt())).thenReturn(getTruck());
        truckService.findById(anyInt());
        verify(truckDao).findById(anyInt());
    }

    @Test
    public void updateTruck() {
        doNothing().when(truckDao).update(any(Truck.class));
        doNothing().when(amqpTemplate).convertAndSend(anyInt());
        truckService.updateTruck(getTruck());
        verify(truckDao).update(any(Truck.class));
    }

    @Test
    public void findSuitableTrucks() {
        when(truckDao.findSuitableTrucks(anyInt())).thenReturn(new LinkedList());
        assertEquals(truckService.findSuitableTrucks(new LinkedList<>()).size(),0);
    }

    @Test
    public void findByNumber() {
        when(truckDao.findByNumber("test")).thenReturn(anyListOf(Truck.class));
        truckService.findByNumber("test");
        verify(truckDao).findByNumber("test");
    }

    @Test
    public void findAllForAdmin() {
        when(truckDao.findAllForAdmin()).thenReturn(new LinkedList<>());
        truckService.findAllForAdmin();
        verify(truckDao).findAllForAdmin();
    }
}