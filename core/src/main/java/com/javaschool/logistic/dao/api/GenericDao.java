package com.javaschool.logistic.dao.api;

public interface GenericDao<T> {

    void create(T entity);
    void update(T entity);
    void delete(T entity);

}
