package com.javaschool.logistic.controller.manager;

import com.javaschool.logistic.model.*;
import com.javaschool.logistic.models.Waypoint;
import com.javaschool.logistic.service.api.*;

import com.javaschool.logistic.utils.DistanceCalculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;


@Controller
@Scope("session")
public class ManageOrderController {

    @Autowired
    private CityService cityService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private TruckService truckService;

    @Autowired
    private DistanceCalculator distanceCalculator;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CargoService cargoService;

    private List<Waypoint> waypoints = new LinkedList<>();

    @GetMapping(value = "/manager_/orders/newcargo")
    public String newCargo(Model model){
        model.addAttribute("waypoint", new Waypoint());
        return "managersPages/newpoint";
    }

    @PostMapping(value = "/manager_/orders/newcargo")
    public String createNewCargo(@ModelAttribute Waypoint waypoint, BindingResult bindingResult){
        if(waypoint.getCargo().getWeight()<0){
            bindingResult.addError(new FieldError("waypoint","cargo.weight","Weight can't be negative!"));
            return "managersPages/newpoint";
        }
        waypoints.add(waypoint);
        return "redirect:/manager_/orders/neworder";
    }

    @GetMapping(value = "/manager_/orders/neworder")
    public String newOrder(Model model){
        model.addAttribute("waypoints",waypoints);
        model.addAttribute("order", new Order());
        model.addAttribute("trucks",truckService.findSuitableTrucks(waypoints));
        return "managersPages/neworder";
    }



    @GetMapping(value = "/manager_/orders/delete_point_{id}")
    public String deletePoint(@PathVariable int id){
        waypoints.remove(id);
        return "redirect:/manager_/orders/neworder";
    }


    @PostMapping(value = "/manager_/orders/neworder")
    public String addDriverInOrder(@ModelAttribute Order order, Model model, BindingResult bindingResult){

        if(waypoints.isEmpty()||order.getTruck()==null){
            bindingResult.addError(new FieldError("order","truck","Waypoints and truck can't be empty!"));
            return "managersPages/neworder";
        }

        int distance = distanceCalculator.calculate(order.getTruck(),waypoints);
        model.addAttribute("driverList", driverService.findSuitableDrivers(distance,order.getTruck()));
        model.addAttribute("order", order);
        model.addAttribute("amount",order.getTruck().getMax_drivers());
        model.addAttribute("truck",order.getTruck());
        return "managersPages/add_drivers";
    }

    @PostMapping(value = "/manager_/orders/neworder/finish")
    public String createOrder(@ModelAttribute Order order){
        Truck truck;
            truck = truckService.findById(order.getTruck().getTruck_id());
            orderService.createOrder(waypoints,order);


        truck.setOrder(order);
        truck.setDrivers(order.getTruck().getDrivers());
        for(Driver driver:order.getTruck().getDrivers()){
            driver.setTruck(truck);
        }
        truckService.updateTruck(truck);
        waypoints.clear();
        return "redirect:/manager_/orders?created";
    }

    @GetMapping(value = "/manager_/orders/order_info_{order_id}")
    public String getOrderInfo(@PathVariable int order_id, Model model){

            model.addAttribute("cargoes",cargoService.findByOrderId(order_id) );


        return "managersPages/orderInfo";
    }

    @GetMapping(value = "/manager_/orders/cancel")
    public String cancelCreate(){
        waypoints.clear();
        return "redirect:/manager_/orders";
    }


    @ModelAttribute("cities")
    public List<City> initializeCities() {
        return cityService.findAllCities();
    }

}
