package com.javaschool.logistic.controller.manager;



import com.javaschool.logistic.model.City;
import com.javaschool.logistic.model.Truck;
import com.javaschool.logistic.service.api.CityService;
import com.javaschool.logistic.service.api.TruckService;
import com.javaschool.logistic.validators.TruckFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class ManageTruckController {

    @Autowired
    private CityService cityService;

    @Autowired
    private TruckService truckService;

    @Autowired
    private TruckFormValidator truckFormValidator;



    @GetMapping(value = "/manager_/trucks/newtruck")
    public String newTruckPage(Model model){
        model.addAttribute("truck", new Truck());
        return "managersPages/newtruck";
    }

    @PostMapping(value = "/manager_/trucks/newtruck")
    public String createNewTruck(@ModelAttribute Truck truck,
                                 BindingResult bindingResult, Model model){
        truckFormValidator.customValidate(truck,bindingResult,false);
        if(bindingResult.hasErrors()){
            model.addAttribute("truck", truck);
            return "managersPages/newtruck";
        }else {
            truckService.createTruck(truck);
            return "redirect:/manager_/trucks";
        }

    }

    @GetMapping(value = "/manager_/delete_truck_{truck_id}")
    public String deleteDriver(@PathVariable int truck_id){
        truckService.deleteById(truck_id);
        return "redirect:/manager_/trucks";
    }


    @GetMapping(value = "/manager_/edit_truck_{truck_id}")
    public String editDriver(@PathVariable int truck_id, Model model){
        model.addAttribute("truck", truckService.findById(truck_id));
        return "managersPages/edit_truck";
    }

    @PostMapping(value = "/manager_/edit_truck_{truck_id}")
    public String updateDriver(Truck truck, @PathVariable int truck_id,
                               BindingResult bindingResult, Model model){
        truckFormValidator.customValidate(truck,bindingResult,true);
        if(bindingResult.hasErrors()){
            model.addAttribute("truck", truck);
            return "managersPages/edit_truck";
        }else {
            if(truck.getOrder().getOrder_id()==0)truck.setOrder(null);
            truckService.updateTruck(truck);
            return "redirect:/manager_/trucks";
        }

    }

    @ModelAttribute("cities")
    public List<City> initializeCities() {
        return cityService.findAllCities();
    }

    @ModelAttribute("statuses")
    public Truck.Status[] initializeStatuses(){
        return Truck.Status.values();
    }


}
