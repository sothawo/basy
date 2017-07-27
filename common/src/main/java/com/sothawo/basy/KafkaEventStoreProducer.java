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

    private final KafkaProducer<String, Event> kafkaProducer;
    private final String topic;

    public KafkaEventStoreProducer(@NotNull Properties props, @NotNull String topic) {
        this.kafkaProducer = new KafkaProducer<>(props);
        this.topic = topic;
    }

    @Override
    public void produce(@NotNull Event evt) {
        ProducerRecord<String, Event> record = new ProducerRecord<>(topic, evt);
        logger.debug("send event to kafka({}): {}", topic, evt);
        kafkaProducer.send(record);
    }

    @Override
    public void close() throws Exception {
        kafkaProducer.close();
    }
}
