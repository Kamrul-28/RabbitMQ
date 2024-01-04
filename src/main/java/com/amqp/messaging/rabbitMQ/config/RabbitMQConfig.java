package com.amqp.messaging.rabbitMQ.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.queue.name}")
    private String queue;
    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    @Value("${rabbitmq.queue.json.name}")
    private String jsonQueue;

    @Value("${rabbitmq.routing.json.key}")
    private String jsonRoutingKey;

    //Creating bin for Exchange
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    //Creating bin for Queue1
    @Bean
    public Queue queue() {
        return new Queue(queue);
    }

    //Binding Exchange with Queue1 with key1
    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }

    //Creating bin for Queue2
    @Bean
    public Queue jsonQueue() {
        return new Queue(jsonQueue);
    }
    //Binding Exchange with Queue2 with key2
    @Bean
    public Binding bindingJson(Queue jsonQueue, TopicExchange exchange) {
        return BindingBuilder.bind(jsonQueue).to(exchange).with(jsonRoutingKey);
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}