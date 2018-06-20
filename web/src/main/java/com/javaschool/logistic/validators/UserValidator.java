package com.javaschool.logistic.validators;

import com.javaschool.logistic.models.User;
import com.javaschool.logistic.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;



@Component
@PropertySource(value = { "classpath:validation.properties"})
public class UserValidator implements Validator {

    private UserService userService;

    @Autowired
    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        User user = (User) o;

        if(userService.findByEmail(user.getEmail())!=null){
            errors.rejectValue("user.email","Exist.DriverForm.email");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.email", "NotEmpty.userForm.email");

        if(!user.getEmail().matches("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")) {
            errors.rejectValue("user.email", "Incorrect.user.email");
        }
    }

}
