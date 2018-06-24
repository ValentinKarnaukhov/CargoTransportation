package com.javaschool.logistic.controller;


import com.javaschool.logistic.models.User;
import com.javaschool.logistic.models.Goods;
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

    private UserService userService;

    private TruckService truckService;

    private OrderService orderService;

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public AdminController(UserService userService, TruckService truckService,
                           OrderService orderService, RabbitTemplate rabbitTemplate) {
        this.userService = userService;
        this.truckService = truckService;
        this.orderService = orderService;
        this.rabbitTemplate = rabbitTemplate;
    }

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
        Goods goods = new Goods();
        goods.setId(1);
        goods.setCityTo("Saint-Petersburg");
        goods.setWeight(10);
        rabbitTemplate.convertAndSend("cargoes", goods);
        return "redirect:/admin";
    }

}
