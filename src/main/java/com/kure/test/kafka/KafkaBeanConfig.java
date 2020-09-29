package com.kure.test.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaBeanConfig {

    private final KafkaTemplate kafkaTemplate;

    @Autowired
    public KafkaBeanConfig(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

}
