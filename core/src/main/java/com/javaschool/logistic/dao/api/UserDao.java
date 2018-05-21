package com.javaschool.logistic.dao.api;

import com.javaschool.logistic.model.User;


public interface UserDao extends GenericDao<User> {

	User findByEmail(String email);

	User findByUsername(String username);
}

