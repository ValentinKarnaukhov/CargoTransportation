package com.javaschool.logistic.validators;


import com.javaschool.logistic.models.Driver;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
@PropertySource(value = { "classpath:validation.properties"})
public class DriverFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Driver.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        Driver driver = (Driver) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "first_name", "NotEmpty.driverForm.first_name");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "last_name", "NotEmpty.driverForm.last_name");

        if (!driver.getFirst_name().matches("^\\D*$")) {
            errors.rejectValue("first_name", "Digit.driverForm.firstAndLastName");
        }

        if (!driver.getLast_name().matches("^\\D*$")) {
            errors.rejectValue("last_name", "Digit.driverForm.firstAndLastName");
        }

        if(driver.getWorked_time()<0||driver.getWorked_time()>=300){
            errors.rejectValue("worked_time","Negative.DriverForm.worked_time");
        }



    }
}
