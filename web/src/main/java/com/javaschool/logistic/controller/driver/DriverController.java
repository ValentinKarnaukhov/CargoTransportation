package com.javaschool.logistic.controller.driver;



import com.javaschool.logistic.model.Driver;
import com.javaschool.logistic.model.OrderWaypoint;

import com.javaschool.logistic.service.api.DriverService;
import com.javaschool.logistic.service.api.OrderWaypointService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;


@Controller
public class DriverController {

    @Autowired
    private DriverService driverService;

    @Autowired
    private OrderWaypointService orderWaypointService;

    //TODO-login for security NOT id
    @RequestMapping(value = "/driver/{driver_id}")
    public String loadPageForDriver(Model model,@PathVariable int driver_id){
        Driver driver = driverService.findById(driver_id);
        List<OrderWaypoint> waypoints = new LinkedList<>();
        if(driver.getTruck()!=null) waypoints = orderWaypointService.findByOrderId(driver.getTruck().getOrder().getOrder_id());
        model.addAttribute("driver", driver);
        model.addAttribute("waypoints",waypoints );
        return "driversPages/driver";
    }

    //TODO-change to method post
    @RequestMapping(value = "/driver/{driver_id}/change", method = RequestMethod.GET)
    public String changeStatus(@PathVariable int driver_id, @RequestParam String status){
        Driver driver = driverService.findById(driver_id);
        driver.setStatus(Driver.Status.valueOf(status));
        driverService.updateDriver(driver);
        return "";
    }

    @RequestMapping(value = "/driver/{point_id}/cargo_change")
    public String changePoint(@PathVariable int point_id, @RequestParam String status){
        OrderWaypoint waypoint= orderWaypointService.findById(point_id);
        waypoint.setStatus(OrderWaypoint.Status.valueOf(status));
        orderWaypointService.updatePoint(waypoint);
        return "";
    }

}
