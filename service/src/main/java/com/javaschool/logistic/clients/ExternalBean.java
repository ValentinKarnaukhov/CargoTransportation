package com.javaschool.logistic.clients;


import com.javaschool.logistic.dao.api.ExternalDao;
import com.javaschool.logistic.model.External;
import com.javaschool.logistic.models.Waypoint;
import com.javaschool.logistic.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@Scope(value = WebApplicationContext.SCOPE_APPLICATION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ExternalBean {

    private  ExternalDao externalDao;

    private Mapper mapper;

    private List<External> externals;

    @Autowired
    public ExternalBean(ExternalDao externalDao, Mapper mapper) {
        this.externalDao = externalDao;
        this.mapper = mapper;
    }

    @PostConstruct
    public void init(){
        externals=externalDao.findAll();
    }

    public List<External> getExternals() {
        return externals;
    }

    public void setExternals(List<External> externals) {
        this.externals = externals;
    }

    public void moveToList(List<Waypoint> waypoints, Integer[] ids){
        for(int i:ids){
            for(External external:externals){
                if(external.getExternal_id()==i){
                    waypoints.add(mapper.externalToWaypoint(external));
                    externals.remove(external);
                    break;
                }
            }
        }
    }

    public void moveFromList(List<Waypoint> waypoints){

        for(Waypoint waypoint:waypoints){
            if(waypoint.isExternal()){
                externals.add(mapper.waypointToExternal(waypoint));
            }
        }

    }
}
