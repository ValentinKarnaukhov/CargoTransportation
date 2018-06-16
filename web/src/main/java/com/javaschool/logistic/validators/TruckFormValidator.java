package com.javaschool.logistic.validators;

import com.javaschool.logistic.model.Truck;
import com.javaschool.logistic.service.api.TruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component
@PropertySource(value = { "classpath:validation.properties"})
public class TruckFormValidator implements Validator {

    @Autowired
    private TruckService truckService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Truck.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        Truck truck = (Truck) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"reg_number","NotEmpty.truckForm.reg_number");

        if(truck.getCapacity()<100)errors.rejectValue("capacity","Zero.truckForm.capacity");

        if(truck.getMax_drivers()<=0)errors.rejectValue("max_drivers", "Zero.truckForm.max_driver");

        if(!truck.getReg_number().matches("^(\\D*){2}(\\d){5}$")){
            errors.rejectValue("reg_number", "NonCorrect.truckForm.reg_number");
        }

    }

    public void customValidate(Object o, Errors errors, boolean isEdit){
        validate(o,errors);
        Truck truck = (Truck) o;
        if(!isEdit){
            if(!truckService.findByNumber(truck.getReg_number()).isEmpty()){
                errors.rejectValue("reg_number", "Exist.truckForm.reg_number");
            }
        }
    }
}
