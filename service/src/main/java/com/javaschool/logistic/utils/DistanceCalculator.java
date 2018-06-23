package com.javaschool.logistic.utils;

import com.javaschool.logistic.models.Truck;
import com.javaschool.logistic.models.Waypoint;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DistanceCalculator{

    public int calculate(Truck truck, List<Waypoint> waypoints){

        int res=0;

        double lastLat = truck.getCity().getLatitude();
        double lastLong = truck.getCity().getLongitude();

        for(Waypoint waypoint:waypoints){

            res+=getDistance(lastLat,lastLong,
                    waypoint.getLoadingCity().getLatitude(),
                    waypoint.getLoadingCity().getLongitude());
            res+=getDistance(waypoint.getLoadingCity().getLatitude(),
                    waypoint.getLoadingCity().getLongitude(),
                    waypoint.getUnloadingCity().getLatitude(),
                    waypoint.getUnloadingCity().getLongitude());
            lastLat = waypoint.getUnloadingCity().getLatitude();
            lastLong = waypoint.getUnloadingCity().getLongitude();
        }
        return res;
    }

    private int getDistance(double latitude1, double longitude1,
                            double latitude2, double longitude2){

        int radius = 6371;
        double dLat = Math.toRadians(latitude2 - latitude1);
        double dLong = Math.toRadians(longitude2 - longitude1);
        double lat1 = Math.toRadians(latitude1);
        double lat2 = Math.toRadians(latitude2);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(lat1) * Math.cos(lat2) * Math.sin(dLong / 2) * Math.sin(dLong / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return (int)(c * radius);

    }


}
