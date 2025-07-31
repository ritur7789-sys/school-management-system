package com.school_managment.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "school-events", groupId = "school-group")
    public void consume(String message) {
        System.out.println("Kafka Consumer received: " + message);
    }
}
