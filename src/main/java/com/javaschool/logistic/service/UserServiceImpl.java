package com.javaschool.logistic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaschool.logistic.dao.api.UserDao;
import com.javaschool.logistic.model.User;


@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao dao;

	public List<User> findAllUsers() {
		return dao.findAllUsers();
	}

	
}
