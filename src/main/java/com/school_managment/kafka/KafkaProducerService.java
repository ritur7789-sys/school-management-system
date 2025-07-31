package com.school_managment.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private static final String TOPIC = "school-events";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendEvent(String message) {
        kafkaTemplate.send(TOPIC, message);
        System.out.println("Kafka Producer sent: " + message);
    }
}
