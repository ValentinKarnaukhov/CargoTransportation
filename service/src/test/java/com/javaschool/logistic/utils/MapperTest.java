package com.javaschool.logistic.utils;

import com.javaschool.logistic.models.Cargo;
import com.javaschool.logistic.models.City;
import com.javaschool.logistic.models.External;
import com.javaschool.logistic.models.Waypoint;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class MapperTest {

    @InjectMocks
    private Mapper mapper = new Mapper();

    private External getExternal(){
        External external = new External();
        external.setCityFrom(new City());
        external.setCityTo(new City());
        external.setName("test");
        external.setWeight(10);
        external.setExternal_id(1);
        return external;
    }

    private Waypoint getWaypoint(){
        Cargo cargo = new Cargo();
        cargo.setName("test");
        cargo.setWeight(10);
        return new Waypoint(cargo, new City(),new City(), true,1);
    }

    @Test
    public void externalToWaypoint() {
        External external = mapper.waypointToExternal(getWaypoint());
        assertEquals(external.getExternal_id(),1);
        assertNotNull(external.getCityFrom());
        assertNotNull(external.getCityTo());
        assertEquals(external.getName(),"test");
        assertEquals(external.getWeight(),10);
    }

    @Test
    public void waypointToExternal() {
        Waypoint waypoint = mapper.externalToWaypoint(getExternal());
        assertEquals(waypoint.getCargo().getWeight(),10);
        assertEquals(waypoint.getCargo().getName(),"test");
        assertTrue(waypoint.getCargo().isExternal());
        assertNotNull(waypoint.getLoadingCity());
        assertNotNull(waypoint.getUnloadingCity());
    }
}