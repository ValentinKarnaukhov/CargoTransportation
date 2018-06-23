package com.javaschool.logistic.dao.impl;

import com.javaschool.logistic.dao.api.GenericDao;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class GenericDaoImpl<T> implements GenericDao<T> {


    @PersistenceContext
    private EntityManager entityManager;

    private Class<T> entityClass;


    protected EntityManager getEntityManager(){
        return this.entityManager;
    }

    @Override
    public void create(T entity){
        getEntityManager().persist(entity);
    }

    @Override
    public void update(T entity) {
        getEntityManager().merge(entity);
    }

    @Override
    public void delete(T entity) {getEntityManager().remove(entity);}

    @Override
    public List<T> findAll(){
        return   getEntityManager()
                    .createQuery("Select t from " + entityClass.getSimpleName()  + " t")
                .getResultList();
    }

    @SuppressWarnings("unchecked")
    GenericDaoImpl() {
        this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
