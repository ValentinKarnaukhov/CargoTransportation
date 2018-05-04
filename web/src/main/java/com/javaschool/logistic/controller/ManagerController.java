package com.javaschool.logistic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Valentin
 */

@Controller
public class ManagerController {

    @RequestMapping(value = "/manager_/drivers")
    public String managerToDrivers(){
        return "drivers";


    }@RequestMapping(value = "/manager_/orders")
    public String managerToOrders(){
        return "orders";


    }@RequestMapping(value = "/manager_/trucks")
    public String managerToTrucks(){
        return "trucks";
    }
}
