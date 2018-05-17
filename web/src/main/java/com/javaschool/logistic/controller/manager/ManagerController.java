package com.javaschool.logistic.controller.manager;

import com.javaschool.logistic.model.City;
import com.javaschool.logistic.model.Driver;
import com.javaschool.logistic.model.User;
import com.javaschool.logistic.service.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;

/**
 * @author Valentin
 */

@Controller
public class ManagerController {


    @Autowired
    DriverService driverService;

    @Autowired
    OrderService orderService;

    @Autowired
    TruckService truckService;


    @RequestMapping(value = "/manager_/drivers", method = RequestMethod.GET)
    public String managerToDrivers(Model model){
        model.addAttribute("drivers",driverService.findAllDrivers());
        return "drivers";
    }


    @RequestMapping(value = "/manager_/trucks", method = RequestMethod.GET)
    public String managerToTrucks(Model model){
        model.addAttribute("trucks", truckService.findAllTrucks());
        return "trucks";
    }

    @RequestMapping(value = "/manager_/orders", method = RequestMethod.GET)
    public String managerToOrders(Model model){
        model.addAttribute("orders", orderService.findAllOrders());
        return "orders";
    }


}
