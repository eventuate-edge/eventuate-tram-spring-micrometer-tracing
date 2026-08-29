package io.eventuate.tram.spring.micrometer.tracing.test;

import tools.jackson.core.JacksonException;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;

import java.util.List;

public class ZipkinTraceDeserializer {

    private static final ObjectMapper objectMapper = JsonMapper.builder()
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .build();

    public static List<List<ZipkinSpan>> deserializeTraces(String jsonString) {
        try {
            return objectMapper.readValue(jsonString, new TypeReference<List<List<ZipkinSpan>>>() {});
        } catch (JacksonException e) {
            throw new RuntimeException("Failed to deserialize Zipkin traces: " + jsonString, e);
        }
    }
}
