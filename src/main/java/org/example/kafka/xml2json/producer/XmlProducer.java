package org.example.kafka.xml2json.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Timer;
import java.util.TimerTask;

@Component
public class XmlProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(XmlProducer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    public void starProducing() {
        final TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                ObjectNode objectNode = objectMapper.createObjectNode();
                objectNode.put("nome", "Pele");
                objectNode.put("idade", 90);
                objectNode.put("endereco", "<endereco><rua>Rua A</rua><numero>10</numero><bairro><nome>Sta Efigenia</nome></bairro></endereco>");
                objectNode.put("timestamp", System.currentTimeMillis());
                final String data = objectNode.toString();
                kafkaTemplate.send("inputTopic", null, data);
            }
        };
        final Timer timer = new Timer();
        LOGGER.info("Escalonando produtor de mensagens...");
        timer.schedule(timerTask, 1000L, 1000L);
    }
}
