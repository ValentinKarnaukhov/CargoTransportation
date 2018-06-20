package com.javaschool.logistic.clients;


import com.javaschool.logistic.dao.api.CityDao;
import com.javaschool.logistic.dao.api.ExternalDao;
import com.javaschool.logistic.models.City;
import com.javaschool.logistic.models.External;
import com.javaschool.logistic.models.IncomingMessage;
import com.javaschool.logistic.models.OutgoingMessage;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;


@Component
public class ReceiverBean {

    private CityDao cityDao;

    private ExternalDao externalDao;

    private RabbitTemplate rabbitTemplate;

    private ExternalBean externalBean;

    @Autowired
    public ReceiverBean(CityDao cityDao, ExternalDao externalDao,
                        RabbitTemplate rabbitTemplate, ExternalBean externalBean) {
        this.cityDao = cityDao;
        this.externalDao = externalDao;
        this.rabbitTemplate = rabbitTemplate;
        this.externalBean = externalBean;
    }

    @Transactional
    @RabbitListener(queues = "cargoes")
        public void receive(IncomingMessage incomingMessage) {

        City city = getSuitedCity(incomingMessage.getCityTo(),2);

        if(city!=null){

            External external = new External();
            external.setCityTo(city);
            external.setCityFrom(cityDao.findById(35));
            external.setName(String.valueOf(incomingMessage.getId())+"-external");
            external.setWeight(incomingMessage.getWeight());

            externalDao.create(external);
            externalBean.getExternals().add(external);

            OutgoingMessage outgoingMessage = new OutgoingMessage();
            outgoingMessage.setId(incomingMessage.getId());
            outgoingMessage.setStatus(OutgoingMessage.Status.OK);

            rabbitTemplate.convertAndSend("answers",outgoingMessage);

        }else{

            OutgoingMessage outgoingMessage = new OutgoingMessage();
            outgoingMessage.setId(incomingMessage.getId());
            outgoingMessage.setStatus(OutgoingMessage.Status.REJECTED);

            rabbitTemplate.convertAndSend("answers", outgoingMessage);
        }


    }



    private City getSuitedCity(String cityName, int distance){

        List<City> cityList = cityDao.findAll();
        LevenshteinDistance levenshteinDistance = new LevenshteinDistance();

        City resCity = null;
        int minDistance = distance;
        int currDistance;

        for(City city:cityList){
            currDistance = levenshteinDistance.apply(cityName.toLowerCase(),city.getName().toLowerCase());
            if(currDistance<=minDistance){
                minDistance=currDistance;
                resCity=city;
            }

        }

        return resCity;

    }


    @RabbitListener(queues = "answers")
    public void listen(OutgoingMessage outgoingMessage){
        System.out.println(outgoingMessage);
    }

}
