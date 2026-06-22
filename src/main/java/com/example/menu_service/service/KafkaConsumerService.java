package com.example.menu_service.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

//    @KafkaListener(
//            topics = "vendor-subscription-topic",
//            groupId = "menu-group"
//    )
    public void consume(
            String message) {

        System.out.println(
                "Kafka Message Received : "
                        + message
        );
    }
}