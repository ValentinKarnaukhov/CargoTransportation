package com.javaschool.logistic.dao.impl;

import com.javaschool.logistic.dao.api.GenericDao;
import com.javaschool.logistic.exeption.DaoException;
import org.hibernate.Session;
import org.hibernate.SessionBuilder;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @author Valentin
 */
public abstract class GenericDaoImpl<T> implements GenericDao<T> {

    @PersistenceContext
    EntityManager entityManager;

    private Class<T> entityClass;


    protected EntityManager getEntityManager(){
        return this.entityManager;
    }

    @Override
    public void create(T entity) throws DaoException{
        try {
            getEntityManager().persist(entity);
        }catch (Exception e){
            throw new DaoException();
        }

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
    public GenericDaoImpl() {
        this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
