package com.javaschool.logistic.controller.driver;



import com.javaschool.logistic.model.City;
import com.javaschool.logistic.model.Driver;
import com.javaschool.logistic.model.OrderWaypoint;

import com.javaschool.logistic.service.api.CityService;
import com.javaschool.logistic.service.api.DriverService;
import com.javaschool.logistic.service.api.OrderWaypointService;

import com.javaschool.logistic.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

//TODO add javadoc
@Controller
public class DriverController {

    @Autowired
    private DriverService driverService;

    @Autowired
    private OrderWaypointService orderWaypointService;

    @Autowired
    private UserService userService;

    @Autowired
    private CityService cityService;


    //TODO add email notification
    @RequestMapping(value = "/driver")
    public String loadPageForDriver(Model model){
        int driver_id = userService.findByEmail(getPrincipal()).getDriver().getDriver_id();
        Driver driver = driverService.findById(driver_id);
        List<OrderWaypoint> waypoints = new LinkedList<>();
        if(driver.getTruck()!=null) waypoints = orderWaypointService.findByOrderIdLoad(driver.getTruck().getOrder().getOrder_id());
        model.addAttribute("driver", driver);
        model.addAttribute("waypoints",waypoints );
        return "driversPages/driver";
    }




    private String getPrincipal(){
        String userName;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    @ModelAttribute("cities")
    public List<City> initializeCities() {
        return cityService.findAllCities();
    }

}
