package com.javaschool.logistic.service.api;

import com.javaschool.logistic.models.Truck;
import com.javaschool.logistic.models.Waypoint;

import java.util.List;

public interface TruckService {


    List<Truck> findAllTrucks();

    void createTruck(Truck truck);

    void deleteById(int truck_id);

    Truck findById(int truck_id);

    void updateTruck(Truck truck);

    List<Truck> findSuitableTrucks(List<Waypoint> waypoints);

    List<Truck> findByNumber(String number);

    List<Truck> findAllForAdmin();



}
