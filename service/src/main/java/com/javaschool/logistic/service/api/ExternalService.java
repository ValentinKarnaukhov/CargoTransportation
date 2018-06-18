package com.javaschool.logistic.service.api;

import com.javaschool.logistic.model.External;

import java.util.List;

public interface ExternalService {

    void delete(External external);
    List<External> findAll();
    int getId(String name);
}
