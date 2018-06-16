package com.javaschool.logistic.clients;



import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ReceiverBean {

    @RabbitListener(queues = "cargoes")
    public void receive(String in) {
        System.out.println(in);
    }
}
