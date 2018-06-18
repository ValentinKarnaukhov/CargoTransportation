package com.javaschool.logistic.controller.manager;

import com.javaschool.logistic.service.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Valentin
 */

@Controller
public class ManagerController {

    private DriverService driverService;

    private OrderService orderService;

    private TruckService truckService;

    @Autowired
    public ManagerController(DriverService driverService, OrderService orderService,
                             TruckService truckService) {
        this.driverService = driverService;
        this.orderService = orderService;
        this.truckService = truckService;
    }

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
