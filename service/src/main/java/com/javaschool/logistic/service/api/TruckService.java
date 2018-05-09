package com.javaschool.logistic.service.api;

import com.javaschool.logistic.model.Truck;

import java.util.List;

public interface TruckService {


    List<Truck> findAllTrucks();

    void createTruck(Truck truck);

    void deleteById(int truck_id);

    Truck findById(int truck_id);

    void updateTruck(Truck truck);

}