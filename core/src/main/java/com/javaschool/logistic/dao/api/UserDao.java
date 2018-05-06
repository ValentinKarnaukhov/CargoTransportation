package com.javaschool.logistic.dao.api;

import java.util.List;
import com.javaschool.logistic.model.User;


public interface UserDao extends GenericDao<User> {

	void createUser(User user);
	
	List<User> findAll();

	User findByEmail(String email);

	User findByUsername(String username);
}

