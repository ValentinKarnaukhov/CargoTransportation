package com.javaschool.logistic.controller.manager;

import com.javaschool.logistic.service.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Valentin
 */

@Controller
public class ManagerController {


    @Autowired
    private DriverService driverService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private TruckService truckService;


    @GetMapping(value = "/manager_/drivers")
    public String managerToDrivers(Model model){
        model.addAttribute("drivers",driverService.findAllDrivers());
        return "managersPages/drivers";
    }


    @GetMapping(value = "/manager_/trucks")
    public String managerToTrucks(Model model){
        model.addAttribute("trucks", truckService.findAllTrucks());
        return "managersPages/trucks";
    }

    @GetMapping(value = "/manager_/orders")
    public String managerToOrders(Model model){
        model.addAttribute("orders", orderService.findAllOrders());
        return "managersPages/orders";
    }


}
