package com.javaschool.logistic.utils;


import com.javaschool.logistic.models.City;
import com.javaschool.logistic.models.Truck;
import com.javaschool.logistic.models.Waypoint;
import org.junit.Test;
import org.mockito.InjectMocks;


import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;


public class DistanceCalculatorTest {


    @InjectMocks
    private DistanceCalculator distanceCalculator = new DistanceCalculator();

    private Truck getTruck(){
        Truck truck = new Truck();
        truck.setCity(new City());
        truck.getCity().setLatitude(55.755773);
        truck.getCity().setLongitude(37.617761);
        return truck;
    }

    private List<Waypoint> getWaypoints(){

        List<Waypoint> waypoints = new LinkedList<>();
        Waypoint waypoint = new Waypoint();
        waypoint.setLoadingCity(new City());
        waypoint.setUnloadingCity(new City());

        waypoint.getLoadingCity().setLatitude(59.938806);
        waypoint.getLoadingCity().setLongitude(30.314278);

        waypoint.getUnloadingCity().setLatitude(55.755773);
        waypoint.getUnloadingCity().setLongitude(37.617761);

        waypoints.add(waypoint);

        return waypoints;
    }

    @Test
    public void calculate() {

        assertEquals(distanceCalculator.calculate(getTruck(),getWaypoints()), 1268);

    }
}