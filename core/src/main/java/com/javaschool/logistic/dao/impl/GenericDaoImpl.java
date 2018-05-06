package com.javaschool.logistic.dao.impl;

import com.javaschool.logistic.dao.api.GenericDao;
import org.hibernate.SessionBuilder;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Valentin
 */
public abstract class GenericDaoImpl<T> implements GenericDao<T> {

    @PersistenceContext
    EntityManager entityManager;



    protected EntityManager getEntityManager(){
        return this.entityManager;
    }

    @Override
    public void create(T entity) {
        entityManager.persist(entity);
    }

    @Override
    public void update(T entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(T entity) {entityManager.remove(entity);}


}
