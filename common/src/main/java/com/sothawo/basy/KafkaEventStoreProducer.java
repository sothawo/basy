/*
 * (c) Copyright 2017 sothawo
 */
package com.sothawo.basy;

import com.sothawo.basy.events.Event;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * @author P.J. Meisch (pj.meisch@sothawo.com)
 */
public class KafkaEventStoreProducer implements EventStoreProducer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaEventStoreProducer.class);
    private static final String TOPIC = "basy-account-events";

    private final KafkaProducer<String, Event> kafkaProducer;

    public KafkaEventStoreProducer(@NotNull Properties props) {
        this.kafkaProducer = new KafkaProducer<>(props);
    }

    @Override
    public void produce(@NotNull Event evt) {
        ProducerRecord<String, Event> record = new ProducerRecord<>(TOPIC, evt);
        kafkaProducer.send(record);
    }

    @Override
    public void close() throws Exception {
        kafkaProducer.close();
    }
}
