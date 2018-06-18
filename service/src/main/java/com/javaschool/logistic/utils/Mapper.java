package com.javaschool.logistic.utils;


import com.javaschool.logistic.model.Cargo;
import com.javaschool.logistic.model.External;
import com.javaschool.logistic.models.Waypoint;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public Waypoint externalToWaypoint(External external){
        Cargo cargo = new Cargo();
        cargo.setWeight(external.getWeight());
        cargo.setName(external.getName());
        cargo.setExternal(true);
        return new Waypoint(cargo,external.getCityFrom(),external.getCityTo(),true,external.getExternal_id());
    }

    public External waypointToExternal(Waypoint waypoint){
        External external = new External();
        external.setWeight(waypoint.getCargo().getWeight());
        external.setName(waypoint.getCargo().getName());
        external.setCityFrom(waypoint.getUnloadingCity());
        external.setCityTo(waypoint.getLoadingCity());
        external.setExternal_id(waypoint.getExternal_id());
        return external;
    }


}
