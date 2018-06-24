package com.javaschool.logistic.utils;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;


/**
 * Class sender credentials of remote service
 */
@Component
public class MessagesSender {

    private final String URL;

    @Autowired
    public MessagesSender(Environment environment) {
        this.URL= environment.getProperty("address");
    }

    private static final Logger LOGGER = Logger.getLogger(MessagesSender.class);

    public String send(String username, String email, String password)  {

        MultiValueMap<String, String> requestData = new LinkedMultiValueMap<>();
        requestData.add("username", username);
        requestData.add("email", email);
        requestData.add("password", password);

        RestTemplate restTemplate = new RestTemplate();

        HttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
        HttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
        restTemplate.setMessageConverters(Arrays.asList(new HttpMessageConverter[]{formHttpMessageConverter, stringHttpMessageConverter}));

        ResponseEntity<?> response = restTemplate.postForEntity(URL,requestData,String.class);

        if(!response.getBody().equals("OK")){
            LOGGER.warn(new RestClientException("Message not sanded"));
        }
        return String.valueOf(response.getBody());
    }

}
