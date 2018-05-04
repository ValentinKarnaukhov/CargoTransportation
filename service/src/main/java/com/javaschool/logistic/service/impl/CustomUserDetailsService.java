package com.javaschool.logistic.service.impl;

import com.javaschool.logistic.model.User;
import com.javaschool.logistic.service.api.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Valentin
 */


@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger log = Logger.getLogger(CustomUserDetailsService.class);

    @Autowired
    private UserService userService;


    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String email){

        User user = userService.findByEmail(email);

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),user.isEnabled(),
                true,true,
                true,getGrantedAuthorities(user));
    }

    private List<GrantedAuthority> getGrantedAuthorities(User user){
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_"+user.getRole()));
        log.info("User "+ user.getUsername() + " logged in");
        return authorities;
    }
}
