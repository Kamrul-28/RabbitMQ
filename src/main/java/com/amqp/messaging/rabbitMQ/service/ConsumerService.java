package com.amqp.messaging.rabbitMQ.service;

import com.amqp.messaging.rabbitMQ.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {
    private static final Logger logger = LoggerFactory.getLogger(ConsumerService.class);
    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void consumeMessageFromQueue(String message) {
        logger.info(String.format("Message received: " + message));
    }

    @RabbitListener(queues = {"${rabbitmq.queue.json.name}"})
    public void consumeJsonMessageFromQueue(User user) {
        logger.info(String.format("Message received from queue : " + user.toString()));
    }
}
