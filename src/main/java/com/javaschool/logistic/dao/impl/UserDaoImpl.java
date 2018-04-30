package com.javaschool.logistic.dao.impl;

import java.util.Collection;
import java.util.List;
import com.javaschool.logistic.dao.api.UserDao;
import com.javaschool.logistic.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;


@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {

	@Override
	public void save(User user) {
		create(user);
	}

	@Override
	public List<User> findAllUsers() {
		List<User> users=getEntityManager()
				.createQuery("SELECT u FROM User u ORDER BY u.username ASC")
				.getResultList();
		return users;
	}
}
