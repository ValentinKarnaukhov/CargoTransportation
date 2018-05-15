package com.javaschool.logistic.controller;

import com.javaschool.logistic.model.*;
import com.javaschool.logistic.models.Waypoint;
import com.javaschool.logistic.service.api.CityService;
import com.javaschool.logistic.service.api.DriverService;
import com.javaschool.logistic.service.api.TruckService;

import com.javaschool.logistic.utils.DistanceCalculator;
import com.javaschool.logistic.utils.DriversConverter;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    DistanceCalculator distanceCalculator;

    @Autowired
    DriversConverter driversConverter;


    private List<Waypoint> waypoints = new LinkedList<>();

    private Order order = new Order();

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

    @RequestMapping(value = "/manager_/orders/neworder", method = RequestMethod.GET)
    public String newOrder(Model model){

        model.addAttribute("waypoints",waypoints);
        model.addAttribute("order", order);
        model.addAttribute("trucks",truckService.findSuitableTrucks(waypoints));
        return "neworder";
    }



    @RequestMapping(value = "/manager_/orders/delete_point_{id}")
    public String deletePoint(@PathVariable int id){
        waypoints.remove(id);
        return "redirect:/manager_/orders/neworder";
    }


    @RequestMapping(value = "/manager_/orders/neworder", method = RequestMethod.POST)
    public String addDriverInOrder(@ModelAttribute Order order, Model model){
        this.order=order;
        int distance = distanceCalculator.calculate(order.getTruck(),waypoints);
        model.addAttribute("driverList", driverService.findSuitableDrivers(distance,order.getTruck()));
        model.addAttribute("order", order);
        return "add_drivers";
    }

    @RequestMapping(value = "/manager_/orders/neworder/finish", method = RequestMethod.POST)
    public String createOrder(@ModelAttribute Order order){

//        @RequestParam List<Integer> drivers,
//        for(Driver driver:driversConverter.convert(drivers)){
//            System.out.println(driver.getFirst_name());
//        }




        return "redirect:/manager_/orders";
    }



    @ModelAttribute("cities")
    public List<City> initializeCities() {
        return cityService.findAllCities();
    }





}
