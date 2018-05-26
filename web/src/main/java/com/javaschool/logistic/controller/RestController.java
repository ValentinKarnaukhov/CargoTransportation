package com.javaschool.logistic.controller;


import com.javaschool.logistic.dto.WaypointDto;
import com.javaschool.logistic.model.Driver;
import com.javaschool.logistic.model.OrderWaypoint;
import com.javaschool.logistic.service.api.DriverService;
import com.javaschool.logistic.service.api.OrderWaypointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@org.springframework.web.bind.annotation.RestController
public class RestController {


    @Autowired
    private DriverService driverService;

    @Autowired
    private OrderWaypointService orderWaypointService;

    @RequestMapping(value = "/driver/{driver_id}/change", method = RequestMethod.GET)
    public void changeStatus(@PathVariable int driver_id, @RequestParam String status){
        Driver driver = driverService.findById(driver_id);
        driver.setStatus(Driver.Status.valueOf(status));
        driverService.updateDriver(driver);
    }

    @RequestMapping(value = "/driver/{driver_id}/changeCity", method = RequestMethod.GET)
    public void changeCity(@PathVariable int driver_id, @RequestParam Integer city_id){
        Driver driver = driverService.findById(driver_id);
        driver.getCity().setCity_id(city_id);
        driverService.updateDriver(driver);
    }

    @RequestMapping(value = "/driver/cargo_change", method = RequestMethod.POST)
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

}
