package com.javaschool.logistic.service.impl;



import com.javaschool.logistic.dao.api.UserDao;
import com.javaschool.logistic.model.User;
import com.javaschool.logistic.service.api.UserService;
import com.javaschool.logistic.utils.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PasswordGenerator passwordGenerator;

    @Override
    public void createUser(User user) {
        String password = passwordGenerator.getGeneratedPassword();
        System.out.println(password+"*************************");
        user.setPassword(passwordEncoder.encode(password));
        userDao.create(user);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public void updateUser(User user) {
        userDao.update(user);
    }


}
