package com.javaschool.logistic.service.impl;

import com.javaschool.logistic.dao.api.ExternalDao;
import com.javaschool.logistic.model.External;
import com.javaschool.logistic.service.api.ExternalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ExternalServiceImpl implements ExternalService {

    @Autowired
    private ExternalDao externalDao;


    @Override
    @Transactional
    public void delete(External external) {
        externalDao.delete(external);
    }

    @Override
    @Transactional
    public List<External> findAll() {
        return externalDao.findAll();
    }

    @Override
    public int getId(String name) {
        return Integer.parseInt(name.split("-")[0]);
    }


}
