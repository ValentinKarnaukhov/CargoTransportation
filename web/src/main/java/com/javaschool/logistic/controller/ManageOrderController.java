package com.javaschool.logistic.controller;

import com.javaschool.logistic.model.*;
import com.javaschool.logistic.models.Waypoint;
import com.javaschool.logistic.service.api.CityService;
import com.javaschool.logistic.service.api.DriverService;
import com.javaschool.logistic.service.api.TruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.LinkedList;
import java.util.List;


@Controller
@Scope("session")
public class ManageOrderController {

    @Autowired
    CityService cityService;

    @Autowired
    DriverService driverService;

    @Autowired
    TruckService truckService;


    private List<Waypoint> waypoints = new LinkedList<>();

    @RequestMapping(value = "/manager_/orders/neworder", method = RequestMethod.GET)
    public String newOrder(Model model){
        model.addAttribute("waypoints",waypoints);
        model.addAttribute("order", new Order());
        model.addAttribute("trucks",truckService.findSuitableTrucks(waypoints));
        return "neworder";
    }

    @RequestMapping(value = "/manager_/orders/newcargo", method = RequestMethod.GET)
    public String newCargo(Model model){
        model.addAttribute("waypoint", new Waypoint());
        return "newpoint";
    }

    @RequestMapping(value = "/manager_/orders/newcargo", method = RequestMethod.POST)
    public String createNewCargo(@ModelAttribute Waypoint waypoint){
        waypoints.add(waypoint);
        return "redirect:/manager_/orders/neworder";
    }

    @RequestMapping(value = "/manager_/orders/delete_point_{id}")
    public String deletePoint(@PathVariable int id){
        waypoints.remove(id);
        return "redirect:/manager_/orders/neworder";
    }


    @RequestMapping(value = "/manager_/orders/test", method = RequestMethod.GET)
    public String test(Model model){
        model.addAttribute("drivers", new LinkedList<Driver>());
        return "select_drivers";
    }


    @ModelAttribute("cities")
    public List<City> initializeCities() {
        return cityService.findAllCities();
    }

    @ModelAttribute("drivers")
    public List<Driver> initializeDrivers(){
        return driverService.findAllDrivers();
    }




}
