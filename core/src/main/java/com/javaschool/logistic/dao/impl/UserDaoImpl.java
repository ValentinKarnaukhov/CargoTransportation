package com.javaschool.logistic.dao.impl;


import com.javaschool.logistic.dao.api.UserDao;
import com.javaschool.logistic.model.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;


@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {

	private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

    @Override
    public User findByEmail(String email) {
		User user;
    	try{
			user= (User) getEntityManager()
					.createQuery("SELECT u FROM User u WHERE u.email LIKE :email")
					.setParameter("email",email)
					.getSingleResult();
		}catch (NoResultException e){
    		return null;
		}
		return user;
    }

	@Override
	public User findByUsername(String username) {
    	try {
			return (User) getEntityManager()
					.createQuery("SELECT u FROM User u WHERE u.username LIKE :username")
					.setParameter("username",username)
					.getSingleResult();
		}catch (NoResultException e){
    		LOGGER.error("User doesn't exist",e);
    		return null;
		}

	}
}
