package com.javaschool.logistic.service.impl;

import com.javaschool.logistic.dao.api.CityDao;
import com.javaschool.logistic.models.City;
import com.javaschool.logistic.service.api.CityService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.LinkedList;

import static org.mockito.Mockito.*;

public class CityServiceImplTest {

    @Mock
    private CityDao cityDao;

    @InjectMocks
    private CityService cityService = new CityServiceImpl();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createCity() {
        doNothing().when(cityDao).create(any());
        cityService.createCity(new City());
        verify(cityDao).create(any());
    }

    @Test
    public void findAllTrucks() {
        when(cityDao.findAllTrucks(any())).thenReturn(new LinkedList<>());
        cityService.findAllTrucks(any());
        verify(cityDao).findAllTrucks(any());
    }

    @Test
    public void findAllCities() {
        when(cityDao.findAll()).thenReturn(new LinkedList<>());
        cityService.findAllCities();
        verify(cityDao).findAll();

    }
}