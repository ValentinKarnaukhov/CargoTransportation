package com.javaschool.logistic.dao.api;

import com.javaschool.logistic.model.External;

public interface ExternalDao extends GenericDao<External> {

    void deleteById(int id);

}
