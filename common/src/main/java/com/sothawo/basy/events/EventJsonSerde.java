/*
 * (c) Copyright 2017 sothawo
 */
package com.sothawo.basy.events;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * Serialization/Deserialization of Events to JSON.
 *
 * @author P.J. Meisch (pj.meisch@sothawo.com)
 */
public class EventJsonSerde {

    private final ObjectMapper mapper;

    public EventJsonSerde() {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.enableDefaultTyping();
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.WRAPPER_OBJECT);
    }

    @NotNull
    public String serialize(@NotNull Event event) throws JsonProcessingException {
        return mapper.writeValueAsString(event);
    }

    @NotNull
    public Event deserialize(@NotNull String json) throws IOException {
        return mapper.readValue(json, Event.class);
    }
}
