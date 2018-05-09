package com.javaschool.logistic.dao.api;

import java.util.List;

public interface GenericDao<T> {

    void create(T entity);
    void update(T entity);
    void delete(T entity);
    List<T> findAll();

}
