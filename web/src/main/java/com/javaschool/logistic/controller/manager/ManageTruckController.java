package com.javaschool.logistic.controller.manager;



import com.javaschool.logistic.model.City;
import com.javaschool.logistic.model.Truck;
import com.javaschool.logistic.service.api.CityService;
import com.javaschool.logistic.service.api.TruckService;
import com.javaschool.logistic.validators.TruckFormValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@Controller
public class ManageTruckController {

    private static final Logger LOGGER = Logger.getLogger(ManageTruckController.class);

    private CityService cityService;

    private TruckService truckService;

    private TruckFormValidator truckFormValidator;

    private String truckAttribute = "truck";
    private String redirectToTrucksPage = "redirect:/manager_/trucks";

    @Autowired
    public ManageTruckController(CityService cityService, TruckService truckService,
                                 TruckFormValidator truckFormValidator) {
        this.cityService = cityService;
        this.truckService = truckService;
        this.truckFormValidator = truckFormValidator;
    }

    @GetMapping(value = "/manager_/trucks/newtruck")
    public String newTruckPage(Model model){
        model.addAttribute(truckAttribute, new Truck());
        return "managersPages/newtruck";
    }

    @PostMapping(value = "/manager_/trucks/newtruck")
    public String createNewTruck(@ModelAttribute Truck truck,
                                 BindingResult bindingResult, Model model){
        truckFormValidator.customValidate(truck,bindingResult,false);
        if(bindingResult.hasErrors()){
            model.addAttribute(truckAttribute, truck);
            return "managersPages/newtruck";
        }else {
            truckService.createTruck(truck);
            return redirectToTrucksPage;
        }

    }

    @GetMapping(value = "/manager_/delete_truck_{truck_id}")
    public String deleteTruck(@PathVariable int truck_id){
        truckService.deleteById(truck_id);
        return redirectToTrucksPage;
    }


    @GetMapping(value = "/manager_/edit_truck_{truck_id}")
    public String editTruck(@PathVariable int truck_id, Model model){
        model.addAttribute(truckAttribute, truckService.findById(truck_id));
        return "managersPages/edit_truck";
    }

    @PostMapping(value = "/manager_/edit_truck_{truck_id}")
    public String updateTruck(Truck truck, @PathVariable int truck_id,
                               BindingResult bindingResult, Model model){
        truckFormValidator.customValidate(truck,bindingResult,true);
        if(bindingResult.hasErrors()){
            model.addAttribute(truckAttribute, truck);
            return "managersPages/edit_truck";
        }else {
            if(truck.getOrder().getOrder_id()==0)truck.setOrder(null);
            try{
                truckService.updateTruck(truck);
            }catch (DataIntegrityViolationException e){
                LOGGER.warn(e);
                return redirectToTrucksPage+"?error=1";
            }
            return redirectToTrucksPage;
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
