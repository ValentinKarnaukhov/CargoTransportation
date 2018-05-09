package com.javaschool.logistic.dao.impl;


import java.util.List;
import com.javaschool.logistic.dao.api.UserDao;
import com.javaschool.logistic.model.User;
import org.springframework.stereotype.Repository;



@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {


    @Override
    public User findByEmail(String email) {
		User user= (User) getEntityManager()
				.createQuery("SELECT u FROM User u WHERE u.email LIKE :email")
				.setParameter("email",email)
				.getSingleResult();
		return user;
    }

	@Override
	public User findByUsername(String username) {
		User user= (User) getEntityManager()
				.createQuery("SELECT u FROM User u WHERE u.username LIKE :username")
				.setParameter("username",username)
				.getSingleResult();
		return user;
	}
}
