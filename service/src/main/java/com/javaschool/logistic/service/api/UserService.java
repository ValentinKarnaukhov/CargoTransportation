package com.javaschool.logistic.service.api;


import com.javaschool.logistic.model.User;

public interface UserService {

    void createUser(User user);

    User findByEmail(String email);

    User findByUsername(String username);

    void updateUser(User user);
}
