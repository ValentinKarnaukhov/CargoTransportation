package com.javaschool.logistic.service.impl;


import com.javaschool.logistic.dao.api.TruckDao;
import com.javaschool.logistic.models.Truck;
import com.javaschool.logistic.models.Waypoint;
import com.javaschool.logistic.service.api.TruckService;
import org.apache.log4j.Logger;
import org.springframework.amqp.AmqpConnectException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.LinkedList;
import java.util.List;

@Service
public class TruckServiceImpl implements TruckService {

    private static final Logger LOGGER = Logger.getLogger(TruckServiceImpl.class);

    private TruckDao truckDao;

    private AmqpTemplate amqpTemplate;

    @Autowired
    public TruckServiceImpl(TruckDao truckDao, AmqpTemplate amqpTemplate) {
        this.truckDao = truckDao;
        this.amqpTemplate = amqpTemplate;
    }

    public TruckServiceImpl() {

    }


    @Override
    @Transactional
    public List<Truck> findAllTrucks() {
        return truckDao.findAll();
    }


    @Override
    @Transactional
    public void createTruck(Truck truck) {
        truck.setReg_number(truck.getReg_number().toUpperCase());
        truckDao.create(truck);
        try {
            amqpTemplate.convertAndSend("infoQueue", "update");
        }catch (AmqpConnectException e){
            LOGGER.warn("Queue server not available", e);
        }
        LOGGER.info("Truck "+truck+" has been created");
    }


    /**
     * not remove from database only change enable status
     * @param truck_id truck id
     */
    @Override
    @Transactional
    public void deleteById(int truck_id) {
        Truck truck = findById(truck_id);
        truck.setEnabled(false);
        truckDao.update(truck);
        try {
            amqpTemplate.convertAndSend("infoQueue", "update");
        }catch (AmqpConnectException e){
            LOGGER.warn("Queue server not available", e);
        }
        LOGGER.info("Truck "+truck+" has been removed");
    }


    @Override
    @Transactional
    public Truck findById(int truck_id) {
        return truckDao.findById(truck_id);
    }


    @Override
    @Transactional
    public void updateTruck(Truck truck) {
        truck.setReg_number(truck.getReg_number().toUpperCase());
        truckDao.update(truck);
        try {
            amqpTemplate.convertAndSend("infoQueue", "update");
        }catch (AmqpConnectException e){
            LOGGER.warn("Queue server not available", e);
        }
        LOGGER.info("Truck "+truck+" has been updated");
    }


    @Override
    @Transactional
    public List<Truck> findSuitableTrucks(List<Waypoint> waypoints) {
        if(waypoints.isEmpty()){
            return new LinkedList<>();
        }else {
            return truckDao.findSuitableTrucks(getMaxWeight(waypoints));
        }
    }


    @Override
    @Transactional
    public List<Truck> findByNumber(String number) {
        return truckDao.findByNumber(number);
    }


    @Override
    @Transactional
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
