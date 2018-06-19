package com.javaschool.logistic.service.impl;

import com.javaschool.logistic.dao.api.UserDao;
import com.javaschool.logistic.model.User;
import com.javaschool.logistic.service.api.UserService;
import com.javaschool.logistic.utils.MessagesSender;
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

    private UserDao userDao;

    private PasswordEncoder passwordEncoder;

    private PasswordGenerator passwordGenerator;

    private MessagesSender messagesSender;

    @Autowired
    public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder,
                           PasswordGenerator passwordGenerator, MessagesSender messagesSender) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.passwordGenerator = passwordGenerator;
        this.messagesSender = messagesSender;
    }

    @Transactional
    @Override
    public void createUser(User user) {
        String password = passwordGenerator.getGeneratedPassword();
        user.setPassword(passwordEncoder.encode(password));
        messagesSender.send(user.getUsername(),user.getEmail(),password);
        userDao.create(user);
        LOGGER.info("User "+user+" has been created" );
    }


    @Override
    @Transactional
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }


    @Override
    @Transactional
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }


    @Override
    @Transactional
    public void updateUser(User user) {
        userDao.update(user);
    }


    @Override
    @Transactional
    public List<User> findAll() {
        return userDao.findAll();
    }


}
