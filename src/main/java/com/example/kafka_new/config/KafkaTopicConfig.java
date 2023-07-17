package com.example.kafka_new.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic actorTopic() {
        return TopicBuilder.name("actor").partitions(5).config(TopicConfig.RETENTION_MS_CONFIG, "3600000").build();
    }
}
