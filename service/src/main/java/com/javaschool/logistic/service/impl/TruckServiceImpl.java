package com.javaschool.logistic.service.impl;


import com.javaschool.logistic.dao.api.TruckDao;
import com.javaschool.logistic.model.Truck;
import com.javaschool.logistic.service.api.TruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
    public void deleteById(int driver_id) {
        truckDao.deleteById(driver_id);
    }

    @Override
    public Truck findById(int truck_id) {
        return truckDao.findById(truck_id);
    }

    @Override
    public void updateTruck(Truck truck) {
        truckDao.update(truck);
    }
}
