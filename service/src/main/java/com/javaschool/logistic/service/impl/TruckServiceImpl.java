package com.javaschool.logistic.service.impl;


import com.javaschool.logistic.dao.api.TruckDao;
import com.javaschool.logistic.model.Truck;
import com.javaschool.logistic.models.Waypoint;
import com.javaschool.logistic.service.api.TruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class TruckServiceImpl implements TruckService {

    @Autowired
    TruckDao truckDao;

    @Override
    public List<Truck> findAllTrucks() {
        return truckDao.findAll();
    }

    @Override
    public void createTruck(Truck truck) {
        truckDao.create(truck);
    }



    @Override
    public void deleteById(int truck_id) {
        Truck truck = findById(truck_id);
        truck.setEnabled(false);
        truckDao.update(truck);
    }

    @Override
    public Truck findById(int truck_id) {
        return truckDao.findById(truck_id);
    }

    @Override
    public void updateTruck(Truck truck) {
        truckDao.update(truck);
    }

    @Override
    public List<Truck> findSuitableTrucks(List<Waypoint> waypoints) {
        if(waypoints.isEmpty()){
            return new LinkedList<>();
        }else {
            return truckDao.findSuitableTrucks(getMaxWeight(waypoints));
        }
    }

    @Override
    public List<Truck> findByNumber(String number) {
        return truckDao.findByNumber(number);
    }


    private int getMaxWeight(List<Waypoint> waypoints){
        int max=0;
        for(Waypoint waypoint:waypoints){
            if(max<=waypoint.getCargo().getWeight()) max=waypoint.getCargo().getWeight();
        }
        return max;
    }

}
