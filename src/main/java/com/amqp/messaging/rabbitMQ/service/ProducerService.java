package com.amqp.messaging.rabbitMQ.service;

import com.amqp.messaging.rabbitMQ.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProducerService {

    private static final Logger logger = LoggerFactory.getLogger(ProducerService.class);

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    @Value("${rabbitmq.routing.json.key}")
    private String jsonRoutingKey;

    private final RabbitTemplate rabbitTemplate;

    public ProducerService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    public void sendMessage(String message){
        logger.info(String.format("Message Sent -> %s",message));
        rabbitTemplate.convertAndSend(exchange,routingKey,message);
    }

    public void sendJsonMessage(User user){
        logger.info(String.format("Message Sent -> %s",user));
        rabbitTemplate.convertAndSend(exchange,jsonRoutingKey,user);
    }
}
