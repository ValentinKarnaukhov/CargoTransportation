package com.javaschool.logistic.controller;

import com.javaschool.logistic.model.*;
import com.javaschool.logistic.service.api.CityService;
import com.javaschool.logistic.service.api.DriverService;
import com.javaschool.logistic.service.api.TruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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


    private List<Double> list = new LinkedList<>();

    @RequestMapping(value = "/manager_/orders/neworder", method = RequestMethod.GET)
    public String newOrder(Model model){
        list.add(Math.random());
        model.addAttribute("test",list);
        return "neworder";
    }


    @ModelAttribute("cities")
    public List<City> initializeCities() {
        return cityService.findAllCities();
    }

    @ModelAttribute("drivers")
    public List<Driver> initializeDrivers(){
        return driverService.findAllDrivers();
    }

    @ModelAttribute("trucks")
    public List<Truck> initializeTrucks(){
        return truckService.findAllTrucks();
    }


}
