package com.javaschool.logistic.service.impl;



import com.javaschool.logistic.dao.api.UserDao;
import com.javaschool.logistic.model.User;
import com.javaschool.logistic.service.api.UserService;
import com.javaschool.logistic.utils.EmailService;
import com.javaschool.logistic.utils.MessageWrapper;
import com.javaschool.logistic.utils.PasswordGenerator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("userService")
public class UserServiceImpl implements UserService{

    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);


    @Autowired
    private UserDao userDao;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PasswordGenerator passwordGenerator;

    @Autowired
    private EmailService emailService;

    @Autowired
    private MessageWrapper wrapper;

    @Transactional
    @Override
    public void createUser(User user) {
        String password = passwordGenerator.getGeneratedPassword();
        user.setPassword(passwordEncoder.encode(password));
        userDao.create(user);
        emailService.send(wrapper.getMessage(user.getUsername(),user.getEmail(),password),"wippi2010@rambler.ru");
        LOGGER.info("User "+user+" has been created" );
    }

    @Transactional
    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Transactional
    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        userDao.update(user);
    }

    @Transactional
    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }


}
