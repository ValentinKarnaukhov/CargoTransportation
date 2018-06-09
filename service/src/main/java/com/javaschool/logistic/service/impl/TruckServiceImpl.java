package com.javaschool.logistic.service.impl;


import com.javaschool.logistic.dao.api.TruckDao;
import com.javaschool.logistic.model.Truck;
import com.javaschool.logistic.models.Waypoint;
import com.javaschool.logistic.service.api.TruckService;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class TruckServiceImpl implements TruckService {

    private static final Logger LOGGER = Logger.getLogger(TruckServiceImpl.class);

    @Autowired
    private TruckDao truckDao;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public List<Truck> findAllTrucks() {
        return truckDao.findAll();
    }

    @Override
    public void createTruck(Truck truck) {
        truck.setReg_number(truck.getReg_number().toUpperCase());
        truckDao.create(truck);
        amqpTemplate.convertAndSend("infoQueue", "update");
        LOGGER.info("Truck "+truck+" has been created");
    }



    @Override
    public void deleteById(int truck_id) {
        Truck truck = findById(truck_id);
        truck.setEnabled(false);
        truckDao.update(truck);
        amqpTemplate.convertAndSend("infoQueue", "update");
        LOGGER.info("Truck "+truck+" has been removed");
    }

    @Override
    public Truck findById(int truck_id) {
        return truckDao.findById(truck_id);
    }

    @Override
    public void updateTruck(Truck truck) {
        truck.setReg_number(truck.getReg_number().toUpperCase());
        truckDao.update(truck);
        amqpTemplate.convertAndSend("infoQueue", "update");
        LOGGER.info("Truck "+truck+" has been updated");
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

    @Override
    public List<Truck> findAllForAdmin() {
        return truckDao.findAllForAdmin();
    }


    private int getMaxWeight(List<Waypoint> waypoints){
        int max=0;
        for(Waypoint waypoint:waypoints){
            if(max<=waypoint.getCargo().getWeight()) max=waypoint.getCargo().getWeight();
        }
        return max;
    }

}
