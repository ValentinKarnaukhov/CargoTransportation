package com.javaschool.logistic.service.impl;

import com.javaschool.logistic.dao.api.CargoDao;
import com.javaschool.logistic.model.Cargo;
import com.javaschool.logistic.service.api.CargoService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CargoServiceImplTest {


    @Mock
    private CargoDao cargoDao;

    @InjectMocks
    private CargoService cargoService = new CargoServiceImpl();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    private List<Cargo> getCargoList(){
        List<Cargo> cargos = new LinkedList<>();
        Cargo cargo = new Cargo();
        cargo.setName("TEST");
        cargo.setStatus(Cargo.Status.PREPARE);
        cargo.setWeight(1000);
        cargos.add(cargo);
        cargos.add(cargo);
        return cargos;
    }

    @Test
    public void findByOrderId() {
        when(cargoDao.findByOrderId(anyInt())).thenReturn(getCargoList());
        cargoService.findByOrderId(anyInt());
        verify(cargoDao).findByOrderId(anyInt());
    }
}