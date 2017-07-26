/*
 * (c) Copyright 2017 sothawo
 */
package com.sothawo.basy;

import com.sothawo.basy.events.IdCreated;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.UUID;

/**
 * @author P.J. Meisch (pj.meisch@sothawo.com)
 */
public class IdProducer {
    private static final Logger logger = LoggerFactory.getLogger(IdProducer.class);

    private static final long SLEEP_TIME = 10 * 1_000;

    @NotNull
    private final EventStoreProducer eventStoreProducer;

    public IdProducer(@NotNull EventStoreProducer eventStoreProducer) {
        this.eventStoreProducer = eventStoreProducer;
    }

    public static void main(String[] args) {
        final Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "com.sothawo.basy.events.EventSerde");

        new IdProducer(new KafkaEventStoreProducer(props)).run();
    }

    private void run() {
        try {
            //noinspection InfiniteLoopStatement
            while (true) {
                final IdCreated event = new IdCreated(UUID.randomUUID().toString());
                logger.info("created event: {}", event);
                eventStoreProducer.produce(event);

                Thread.sleep(SLEEP_TIME);
            }
        } catch (InterruptedException e) {
            logger.error("exiting program", e);
        }

    }
}
