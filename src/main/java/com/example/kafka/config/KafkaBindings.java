package com.example.kafka.config;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * Created by jalen on 17-6-20.
 */
public interface KafkaBindings {
    String TEST = "test";

    @Output(KafkaBindings.TEST)
    MessageChannel tsetOut();
}
