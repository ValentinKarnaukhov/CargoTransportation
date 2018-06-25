package com.javaschool.logistic.configuration;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQConfig {

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        return connectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setExchange("exchange");
        return rabbitTemplate;
    }

    @Bean
    public Queue infoQueue(){
        return new Queue("infoQueue");
    }

    @Bean
    public Queue cargoesQueue(){ return  new Queue("delivery-to");}

    @Bean
    public Queue answerQueue(){return new Queue("delivery-from");}

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("exchange");
    }

    @Bean
    public Binding bindingOne(){
        return BindingBuilder.bind(infoQueue()).to(directExchange()).with("infoQueue");
    }

    @Bean
    public Binding bindingTwo(){
        return BindingBuilder.bind(cargoesQueue())
                .to(directExchange()).with("delivery-to");
    }

    @Bean
    public Binding bindingThree(){ return BindingBuilder.bind(answerQueue()).to(directExchange()).with("delivery-from");}

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        return factory;
    }

}
