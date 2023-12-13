package org.example.kafka.xml2json.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class JsonConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonConsumer.class);

    @KafkaListener(id = "myId", topics = "outputTopic")
    public void listen(final String in) {
        LOGGER.info("Consumed: {}", in);
    }
}
