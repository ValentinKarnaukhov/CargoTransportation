package com.javaschool.logistic.validators;

import com.javaschool.logistic.model.Truck;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component
@PropertySource(value = { "classpath:validation.properties" })
public class TruckFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Truck.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        Truck truck = (Truck) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"reg_number","NotEmpty.truckForm.reg_number");

        if(truck.getCapacity()<99)errors.rejectValue("capacity","Zero.truckForm.capacity");

        if(truck.getMax_drivers()==0)errors.rejectValue("max_drivers", "Zero.truckForm.max_driver");

        if(!truck.getReg_number().matches("^([a-z]){2}(\\d){5}$")){
            errors.rejectValue("reg_number", "NonCorrect.truckForm.reg_number");
        }

    }
}
