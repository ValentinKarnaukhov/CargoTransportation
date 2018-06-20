package com.javaschool.logistic.dao.api;

import com.javaschool.logistic.models.User;


public interface UserDao extends GenericDao<User> {

	User findByEmail(String email);

	User findByUsername(String username);
}

