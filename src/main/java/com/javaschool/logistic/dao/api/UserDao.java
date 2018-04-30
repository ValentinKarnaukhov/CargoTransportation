package com.javaschool.logistic.dao.api;

import java.util.List;
import com.javaschool.logistic.model.User;


public interface UserDao {

	void save(User user);
	
	List<User> findAllUsers();
}

