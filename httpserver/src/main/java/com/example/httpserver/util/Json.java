package com.example.httpserver.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;

public class Json {
    private static ObjectMapper objectMapper = defaultObjectMapper();
    private static ObjectMapper defaultObjectMapper() {
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return om;
    }
    
    public static JsonNode parse(String jsonSrc) throws IOException {
        return objectMapper.readTree(jsonSrc);
    }
    
    public static <A> A fromJson(JsonNode node, Class<A> clazz) throws JsonProcessingException {
        return objectMapper.treeToValue(node, clazz);
    }
    
    public static JsonNode tojson(Object obj) {
        return objectMapper.valueToTree(obj);
    }
    
    public static String stringify(JsonNode node) throws JsonProcessingException {
        return gerarJson(node, false);
    }
    
    private static String gerarJson(Object obj, boolean bonitinho) throws JsonProcessingException {
        ObjectWriter ow = objectMapper.writer();
        if (bonitinho) {
            ow = ow.with(SerializationFeature.INDENT_OUTPUT);
        }
        return ow.writeValueAsString(obj);
    }
}
