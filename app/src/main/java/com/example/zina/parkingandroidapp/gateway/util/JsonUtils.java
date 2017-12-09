package com.example.zina.parkingandroidapp.gateway.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;

public class JsonUtils {

    private ObjectMapper objectMapper;

    public String convertObjectToString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e);
        }
    }

    public <T> T convertStringToObject(String body, Class<T> t) {
        try {
            return objectMapper.readValue(body, t);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public static <T> ArrayList<T> toArrayList(T[] elements) {
        ArrayList<T> list = new ArrayList<T>();
        for( T t : elements) {
            list.add(t);
        }
        return list;
    }
}
