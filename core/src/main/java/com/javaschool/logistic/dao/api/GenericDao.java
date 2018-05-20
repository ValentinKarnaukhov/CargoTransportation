package com.javaschool.logistic.dao.api;

import com.javaschool.logistic.exeption.DaoException;

import java.util.List;

public interface GenericDao<T> {

    void create(T entity) throws DaoException;
    void update(T entity) throws DaoException;
    void delete(T entity) throws DaoException;
    List<T> findAll();

}
