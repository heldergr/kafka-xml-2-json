package org.example.kafka.xml2json.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfiguration {

    @Bean
    public NewTopic inputTopic() {
        return TopicBuilder.name("inputTopic")
                .partitions(10)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic outputTopic() {
        return TopicBuilder.name("outputTopic")
                .partitions(10)
                .replicas(1)
                .build();
    }
}
