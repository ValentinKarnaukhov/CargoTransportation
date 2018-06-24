package com.javaschool.logistic.clients;

import com.javaschool.logistic.dao.api.ExternalDao;
import com.javaschool.logistic.models.Cargo;
import com.javaschool.logistic.models.City;
import com.javaschool.logistic.models.External;
import com.javaschool.logistic.models.Waypoint;
import com.javaschool.logistic.utils.Mapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ExternalBeanTest {

    @Mock
    private ExternalDao externalDao;

    @Mock
    private Mapper mapper;

    @InjectMocks
    private ExternalBean externalBean;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void init() {
        when(externalDao.findAll()).thenReturn(new LinkedList<>());
        externalBean.init();
        verify(externalDao).findAll();
    }

    private List<Waypoint> getWaypoints(){
        Waypoint waypoint = new Waypoint(new Cargo(), new City(), new City(), true, 0);
        List<Waypoint> waypoints = new LinkedList<>();
        waypoints.add(waypoint);
        return waypoints;
    }

    @Test
    public void moveToList() {
        when(mapper.waypointToExternal(any())).thenReturn(new External());
        when(mapper.externalToWaypoint(any())).thenReturn(new Waypoint());
        init();
        List<Waypoint> waypoints = getWaypoints();
        externalBean.moveToList(waypoints, new Integer[]{0});
        assertEquals(externalBean.getExternals().size(),0);


    }

    @Test
    public void moveFromList() {
        init();
        externalBean.moveFromList(getWaypoints());
        assertEquals(externalBean.getExternals().size(),1);
    }
}