package com.javaschool.logistic.controller;


import com.javaschool.logistic.model.User;
import com.javaschool.logistic.models.IncomingMessage;
import com.javaschool.logistic.service.api.OrderService;
import com.javaschool.logistic.service.api.TruckService;
import com.javaschool.logistic.service.api.UserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private TruckService truckService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping(value = "/admin")
    public String createManager(@ModelAttribute User user, BindingResult bindingResult, Model model){
        if(userService.findByEmail(user.getEmail())!=null){
            bindingResult.addError(new FieldError("manager","email","Email already exist!"));
        }
        if(bindingResult.hasErrors()){
            model.addAttribute("user", user);
            model.addAttribute("users", userService.findAll());
            model.addAttribute("trucks", truckService.findAllForAdmin());
            model.addAttribute("orders",orderService.findAllOrders());
            return "admin";
        }else {
            userService.createUser(user);
            return "redirect:/admin";
        }

    }

    @GetMapping(value = "/admin")
    public String admin(ModelMap model){
        model.addAttribute("user",new User());
        model.addAttribute("users", userService.findAll());
        model.addAttribute("trucks", truckService.findAllForAdmin());
        model.addAttribute("orders",orderService.findAllOrders());
        return "admin";
    }


    @GetMapping(value = "/test/into")
    public String into(){
        IncomingMessage incomingMessage = new IncomingMessage();
        incomingMessage.setId(1);
        incomingMessage.setCityTo("Saint-Petersburg");
        incomingMessage.setWeight(10);
        rabbitTemplate.convertAndSend("cargoes", incomingMessage);
        return "redirect:/admin";
    }

}
