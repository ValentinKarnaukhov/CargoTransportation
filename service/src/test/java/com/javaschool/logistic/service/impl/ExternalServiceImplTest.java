package com.javaschool.logistic.service.impl;

import com.javaschool.logistic.dao.api.ExternalDao;
import com.javaschool.logistic.models.External;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ExternalServiceImplTest {

    private String name;

    @Mock
    private ExternalDao externalDao;

    @InjectMocks
    private ExternalServiceImpl externalService = new ExternalServiceImpl();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.name = "1-test";
    }

    @Test
    public void delete() {
        doNothing().when(externalDao).delete(any());
        externalService.delete(new External());
        verify(externalDao).delete(any());
    }

    @Test
    public void getId() {

        assertEquals(externalService.getId(name),1);
    }
}