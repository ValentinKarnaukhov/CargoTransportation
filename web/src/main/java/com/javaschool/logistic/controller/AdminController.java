package com.javaschool.logistic.controller;


import com.javaschool.logistic.model.User;
import com.javaschool.logistic.service.api.UserService;
import com.javaschool.logistic.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public String createManager(@ModelAttribute User user, BindingResult bindingResult, Model model){
        if(userService.findByEmail(user.getEmail())!=null){
            bindingResult.addError(new FieldError("manager","email","Email already exist!"));
        }

        if(bindingResult.hasErrors()){
            model.addAttribute("user",user);
            return "admin";
        }else {
            userService.createUser(user);
            return "redirect:/admin";
        }

    }




}
