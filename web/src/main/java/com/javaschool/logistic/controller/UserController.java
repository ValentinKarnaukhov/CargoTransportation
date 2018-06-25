package com.javaschool.logistic.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;


@Controller
public class UserController {


    @GetMapping(value = { "/", "/login"})
    public String loginPage() {

        switch (getRole()){
            case "ROLE_ADMIN":
                return "redirect:/admin";
            case "ROLE_MANAGER":
                return "redirect:/manager_";
            case "ROLE_DRIVER":
                return "redirect:/driver";
            default:
                return "login";
        }
    }


    @GetMapping(value = "/manager_")
    public String manager(ModelMap model){
        model.addAttribute("user", getPrincipal());
        return "redirect:/manager_/orders";
    }


    @GetMapping(value="/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    @GetMapping(value = "/access_denied")
    public String access(Model model){
        model.addAttribute("user", getPrincipal());
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

    private String getRole(){
        List<GrantedAuthority> role = new LinkedList<>();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            role.addAll(((UserDetails) principal).getAuthorities());
        } else {
            role.add(new SimpleGrantedAuthority(principal.toString()));
        }
        return role.get(0).getAuthority();
    }


}
