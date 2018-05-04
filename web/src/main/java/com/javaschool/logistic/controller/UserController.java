package com.javaschool.logistic.controller;


import com.javaschool.logistic.model.Cargo;
import com.javaschool.logistic.model.OrderWaypoint;
import com.javaschool.logistic.service.api.CityService;
import com.javaschool.logistic.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 * @author Valentin
 */
@Controller
public class UserController {


    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    UserService userService;

    @Autowired
    CityService cityService;

    @RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @Transactional
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(ModelMap model){
        model.addAttribute("user", getPrincipal());
        return "admin";
    }

    @RequestMapping(value = "/manager_", method = RequestMethod.GET)
    public String manager(ModelMap model){
        model.addAttribute("user", getPrincipal());
        return "manager";
    }

    @RequestMapping(value = "/driver", method = RequestMethod.GET)
    public String driver(ModelMap model){
        model.addAttribute("user", getPrincipal());
        return "driver";
    }

    @RequestMapping(value = "/access_denied", method = RequestMethod.GET)
    public String access(){
        return "accessDenied";
    }

    private String getPrincipal(){
        String userName;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }




}
