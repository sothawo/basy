/*
 * (c) Copyright 2017 sothawo
 */
package com.sothawo.basy.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * @author P.J. Meisch (pj.meisch@sothawo.com)
 */
public class EventSerde implements Serializer<Event>, Deserializer<Event> {

    private static final Charset CHARSET = Charset.forName("UTF-8");

    private EventJsonSerde eventJsonSerde = new EventJsonSerde();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public byte[] serialize(String topic, Event event) {
        try {
            return eventJsonSerde.serialize(event).getBytes(CHARSET);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("cannot serialize " + event.toString(), e);
        }
    }

    @Override
    public void close() {
    }

    @Override
    public Event deserialize(String topic, byte[] data) {
        final String json = new String(data, CHARSET);
        try {
            return eventJsonSerde.deserialize(json);
        } catch (IOException e) {
            throw new IllegalArgumentException("cannot serialize " + json, e);
        }
    }
}
