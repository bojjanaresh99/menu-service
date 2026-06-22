package com.example.menu_service.service;

import com.example.menu_service.dto.OrderEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    private static final String TOPIC =
            "order-topic";

    public KafkaProducerService(
            KafkaTemplate<String, OrderEvent> kafkaTemplate) {

        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrder(
            OrderEvent event) {

        kafkaTemplate.send(
                TOPIC,
                event);

        System.out.println(
                "ORDER SENT TO KAFKA");
    }
}