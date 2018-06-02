package com.javaschool.logistic.service.impl;

import com.javaschool.logistic.dao.api.DriverDao;
import com.javaschool.logistic.dao.api.OrderDao;
import com.javaschool.logistic.dao.api.TruckDao;
import com.javaschool.logistic.model.*;
import com.javaschool.logistic.models.JsonResponse;
import com.javaschool.logistic.models.ScoreboardRow;
import com.javaschool.logistic.service.api.ScoreboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;


@Service
@Transactional
public class ScoreboardServiceImpl implements ScoreboardService {


    @Autowired
    private OrderDao orderDao;

    @Autowired
    private TruckDao truckDao;

    @Autowired
    private DriverDao driverDao;

    @Override
    public JsonResponse getResponse() {
        return createResponse();
    }


    private JsonResponse createResponse(){

        JsonResponse jsonResponse=new JsonResponse();

        List<Order> orders = orderDao.findLastTen();
        List<ScoreboardRow> rows = new LinkedList<>();
        for(Order order:orders){
            List<OrderWaypoint> waypoints = order.getOrderWaypoints();
            String cityFrom=waypoints.get(0).getCity().getName();
            String cityTo=waypoints.get(waypoints.size()-1).getCity().getName();
            ScoreboardRow row=new ScoreboardRow(order.getOrder_id(),
                    cityFrom,cityTo,order.getOrderHistory().getTruck().getReg_number(),
                    waypoints.size()/2,order.isComplete());
            row.setDrivers(getDriversRow(order));
            rows.add(row);
        }

        jsonResponse.setRows(rows);
        List<Driver> drivers = driverDao.findAll();
        List<Truck> trucks=truckDao.findAll();
        jsonResponse.setDriversTotal(drivers.size());
        jsonResponse.setDriversBusy(getBusyDrivers(drivers));
        jsonResponse.setTrucksBroken(getBrokenTrucks(trucks));
        jsonResponse.setTrucksTotal(trucks.size());
        jsonResponse.setTrucksOrder(getOrderTrucks(trucks));
        return jsonResponse;

    }

    private int getBusyDrivers(List<Driver> drivers){

        int res=0;

        for(Driver driver:drivers){
            if(driver.getTruck()!=null){
                res++;
            }
        }
        return res;
    }

    private int getBrokenTrucks(List<Truck> trucks){

        int res=0;

        for(Truck truck:trucks){
            if(truck.getStatus().equals(Truck.Status.BROKEN))
                res++;
        }
        return res;
    }

    private int getOrderTrucks(List<Truck> trucks){

        int res=0;

        for(Truck truck:trucks){
            if(truck.getOrder()!=null){
                res++;
            }
        }

        return res;
    }

    private List<String> getDriversRow(Order order){

        List<String> res=new LinkedList<>();

        for(Driver driver:order.getOrderHistory().getDrivers()){
            res.add(driver.getPersonal_code()
            +"-"+driver.getFirst_name()+" "+driver.getLast_name());
        }
        return res;

    }






}
