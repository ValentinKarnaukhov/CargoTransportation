package com.javaschool.logistic.controller.manager;


import com.javaschool.logistic.model.City;
import com.javaschool.logistic.model.Driver;
import com.javaschool.logistic.model.User;
import com.javaschool.logistic.service.api.CityService;
import com.javaschool.logistic.service.api.DriverService;
import com.javaschool.logistic.service.api.UserService;
import com.javaschool.logistic.validators.DriverFormValidator;
import com.javaschool.logistic.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class ManageDriverController {

    private DriverService driverService;

    private CityService cityService;

    private UserService userService;

    private DriverFormValidator driverFormValidator;

    private UserValidator userValidator;

    @Autowired
    public ManageDriverController(DriverService driverService, CityService cityService,
                                  UserService userService, DriverFormValidator driverFormValidator,
                                  UserValidator userValidator) {
        this.driverService = driverService;
        this.cityService = cityService;
        this.userService = userService;
        this.driverFormValidator = driverFormValidator;
        this.userValidator = userValidator;
    }

    private String driverAttribute = "driver";
    private String redirectToDriverPage = "redirect:/manager_/drivers";

    @GetMapping(value = "/manager_/drivers/newdriver")
    public String newDriverPage(Model model){
        model.addAttribute(driverAttribute, new Driver());
        return "managersPages/newdriver";
    }

    @PostMapping(value = "/manager_/drivers/newdriver")
    public String createNewDriver(@ModelAttribute Driver driver,
                                  BindingResult driverResult, Model model){
        driverFormValidator.validate(driver, driverResult);
        userValidator.validate(driver.getUser(),driverResult);
        if(driverResult.hasErrors()){
            model.addAttribute(driverAttribute, driver);
            return "managersPages/newdriver";
        }else {
            driver.getUser().setRole(User.Role.DRIVER);
            driver.getUser().setUsername(driver.getFirst_name());
            userService.createUser(driver.getUser());
            driverService.createDriver(driver);
            return redirectToDriverPage;
        }

    }

    @GetMapping(value = "/manager_/delete_driver_{driver_id}")
    public String deleteDriver(@PathVariable int driver_id){
        driverService.deleteById(driver_id);
        return redirectToDriverPage;
    }

    @GetMapping(value = "/manager_/edit_driver_{driver_id}")
    public String editDriver(@PathVariable int driver_id, Model model){
        model.addAttribute(driverAttribute, driverService.findById(driver_id));
        return "managersPages/edit_driver";
    }

    @PostMapping(value = "/manager_/edit_driver_{driver_id}")
    public String updateDriver(@ModelAttribute  Driver driver, @PathVariable int driver_id,
                               BindingResult bindingResult, Model model){
        driverFormValidator.validate(driver,bindingResult);
        if(bindingResult.hasErrors()){
            model.addAttribute(driverAttribute, driver);
            return "managersPages/edit_driver";
        }else {
            if(driver.getTruck().getTruck_id()==0)driver.setTruck(null);
            driverService.updateDriver(driver);
            return redirectToDriverPage;
        }

    }

    //TODO add javadocs
    @GetMapping(value = "/manager_/drivers/updateTime")
    public String updateTime(){
        driverService.setWorktimeForAll(0,new Date());
        return redirectToDriverPage;
    }

    @ModelAttribute("cities")
    public List<City> initializeCities() {
        return cityService.findAllCities();
    }

    @ModelAttribute("statuses")
    public Driver.Status[] initializeStatuses(){
        return Driver.Status.values();
    }


}
