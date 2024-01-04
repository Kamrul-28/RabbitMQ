package com.amqp.messaging.rabbitMQ.controller;

import com.amqp.messaging.rabbitMQ.dto.User;
import com.amqp.messaging.rabbitMQ.service.ProducerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class rabbitMQController {

    private final ProducerService producerService;

    public rabbitMQController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @GetMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message){
        producerService.sendMessage(message);

        return ResponseEntity.ok("Message Send to rabbitMQ Successfully!!");
    }

    @PostMapping("/json/publish")
    public ResponseEntity<String> sendJsonMessage(@RequestBody User user){
        producerService.sendJsonMessage(user);

        return ResponseEntity.ok("Message Send to rabbitMQ Successfully!!");
    }
}
