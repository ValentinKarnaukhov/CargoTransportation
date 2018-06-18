package com.javaschool.logistic.controller;


import com.javaschool.logistic.models.WaypointDto;
import com.javaschool.logistic.model.Driver;
import com.javaschool.logistic.model.OrderWaypoint;
import com.javaschool.logistic.model.Truck;
import com.javaschool.logistic.service.api.DriverService;
import com.javaschool.logistic.service.api.OrderWaypointService;
import com.javaschool.logistic.service.api.TruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    private DriverService driverService;

    private OrderWaypointService orderWaypointService;

    private TruckService truckService;

    @Autowired
    public RestController(DriverService driverService, OrderWaypointService orderWaypointService,
                          TruckService truckService) {
        this.driverService = driverService;
        this.orderWaypointService = orderWaypointService;
        this.truckService = truckService;
    }

    @GetMapping(value = "/driver/{driver_id}/change")
    public void changeStatus(@PathVariable int driver_id, @RequestParam String status){
        Driver driver = driverService.findById(driver_id);
        driver.setStatus(Driver.Status.valueOf(status));
        driverService.updateDriver(driver);
    }

    @GetMapping(value = "/driver/{driver_id}/changeCity")
    public void changeCity(@PathVariable int driver_id, @RequestParam Integer city_id){
        Driver driver = driverService.findById(driver_id);
        driver.getCity().setCity_id(city_id);
        driverService.updateDriver(driver);
    }

    @PutMapping(value = "/driver/cargo_change")
    public @ResponseBody WaypointDto changePoint(@RequestBody Map<String,Object> point){
        OrderWaypoint waypoint= orderWaypointService.findById(Integer.parseInt(point.get("point_id").toString()));
        waypoint.setStatus(OrderWaypoint.Status.valueOf(point.get("status").toString()));
        if(orderWaypointService.updatePoint(waypoint))return new WaypointDto();
        OrderWaypoint orderWaypoint = orderWaypointService.findUnloadByCargoId(waypoint.getCargo().getCargo_id());
        return new WaypointDto(orderWaypoint.getOrder_waypoint_id(),
                orderWaypoint.getCargo().getNumber(),
                orderWaypoint.getCargo().getName(),
                orderWaypoint.getCargo().getWeight(),
                orderWaypoint.getCity().getName(),
                orderWaypoint.getStatus().name());
    }

    @GetMapping(value = "/truck/changeStatus")
    public void changeTruckStatus(@RequestParam String number, @RequestParam String status){
        Truck truck = truckService.findByNumber(number).get(0);
        truck.setStatus(Truck.Status.valueOf(status));
        truckService.updateTruck(truck);
    }
}
