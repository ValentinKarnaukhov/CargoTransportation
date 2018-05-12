package com.javaschool.logistic.controller;


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

import java.util.List;

@Controller
public class ManageDriverController {


    @Autowired
    DriverService driverService;

    @Autowired
    CityService cityService;

    @Autowired
    UserService userService;

    @Autowired
    DriverFormValidator driverFormValidator;

    @Autowired
    UserValidator userValidator;

    @RequestMapping(value = "/manager_/drivers/newdriver", method = RequestMethod.GET)
    public String newDriverPage(Model model){
        model.addAttribute("driver", new Driver());
        return "newdriver";
    }

    @RequestMapping(value = "/manager_/drivers/newdriver", method = RequestMethod.POST)
    public String createNewDriver(@ModelAttribute Driver driver,
                                  BindingResult driverResult, Model model){
        driverFormValidator.validate(driver, driverResult);
        userValidator.validate(driver.getUser(),driverResult);
        if(driverResult.hasErrors()){
            model.addAttribute("driver", driver);
            return "newdriver";
        }else {
            driver.getUser().setRole(User.Role.DRIVER);
            driver.getUser().setUsername(driver.getFirst_name());
            userService.createUser(driver.getUser());
            driverService.createDriver(driver);
            return "redirect:/manager_/drivers";
        }

    }

    @RequestMapping(value = "/manager_/delete_driver_{driver_id}")
    public String deleteDriver(@PathVariable int driver_id){
        driverService.deleteById(driver_id);
        return "redirect:/manager_/drivers";
    }

    @RequestMapping(value = "/manager_/edit_driver_{driver_id}")
    public String editDriver(@PathVariable int driver_id, Model model){
        model.addAttribute("driver", driverService.findById(driver_id));
        return "edit_driver";
    }

    @RequestMapping(value = "/manager_/edit_driver_{driver_id}", method = RequestMethod.POST)
    public String updateDriver(@ModelAttribute  Driver driver, @PathVariable int driver_id,
                               BindingResult bindingResult, Model model){
        driverFormValidator.validate(driver,bindingResult);
        if(bindingResult.hasErrors()){
            model.addAttribute("driver", driver);
            return "edit_driver";
        }else {
            driverService.updateDriver(driver);
            return "redirect:/manager_/drivers";
        }

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
