/*
 * (c) Copyright 2017 sothawo
 */
package com.sothawo.basy;

import com.sothawo.basy.events.Event;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Properties;
import java.util.function.Consumer;

/**
 * @author P.J. Meisch (pj.meisch@sothawo.com)
 */
public class KafkaEventStoreConsumer implements EventStoreConsumer {

    private final KafkaConsumer<String, Event> kafkaConsumer;

    public KafkaEventStoreConsumer(Properties props) {
        this.kafkaConsumer = new KafkaConsumer<>(props);
    }

    @Override
    public void consume(Consumer<Event> consumer) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public void close() throws Exception {
        throw new UnsupportedOperationException("not implemented");
    }
}
