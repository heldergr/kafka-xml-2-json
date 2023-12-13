package org.example.kafka.xml2json.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class JsonExtractorService {
    final ObjectMapper objectMapper = new ObjectMapper();

    public String extractXmlAsJson(final String jsonString, final String nomeCampoXml) {
        try {
            final HashMap hashMap = objectMapper.readValue(jsonString, HashMap.class);
            if (hashMap.containsKey(nomeCampoXml)) {
                Object xml = hashMap.get(nomeCampoXml);
                if (xml != null) {
                    hashMap.put(nomeCampoXml, this.extractXmlAsJson(xml.toString()));
                }
            }
            return objectMapper.writeValueAsString(hashMap);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String extractXmlAsJson(final String xml) throws JsonProcessingException {
        final XmlMapper xmlMapper = new XmlMapper();
        final Map<String, Object> map = xmlMapper.readValue(xml, HashMap.class);

        final ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(map);
    }

    public static void main(String[] args) throws JsonProcessingException {
        JsonExtractorService jsonExtractorService = new JsonExtractorService();
        final var wholeJson = "{\"nome\":\"Pele\",\"idade\":90,\"endereco\":\"<endereco><rua>Rua A</rua><numero>10</numero><bairro><nome>Sta Efigenia</nome></bairro></endereco>\",\"timestamp\":1702501236265}";
        System.out.println(jsonExtractorService.extractXmlAsJson(wholeJson, "endereco"));
    }
}
