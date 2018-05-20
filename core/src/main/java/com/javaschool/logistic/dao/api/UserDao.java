package com.javaschool.logistic.dao.api;

import java.util.List;

import com.javaschool.logistic.exeption.DaoException;
import com.javaschool.logistic.model.User;


public interface UserDao extends GenericDao<User> {

	User findByEmail(String email) throws DaoException;

	User findByUsername(String username) throws DaoException;
}

