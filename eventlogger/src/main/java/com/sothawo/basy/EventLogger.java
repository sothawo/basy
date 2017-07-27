/*
 * (c) Copyright 2017 sothawo
 */
package com.sothawo.basy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.UUID;

/**
 * @author P.J. Meisch (pj.meisch@sothawo.com)
 */
public class EventLogger {
    private static final Logger logger = LoggerFactory.getLogger(EventLogger.class);

    public static void main(String[] args) {
        new EventLogger().run();
    }

    private void run() {
        final Properties props = new KafkaProperties();
        props.put("group.id", UUID.randomUUID().toString());

        try (EventStoreConsumer eventStoreConsumer = new KafkaEventStoreConsumer(props)) {

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    eventStoreConsumer.close();
                } catch (Exception e) {
                    logger.error("error on shutdown", e);
                }
            }));

            eventStoreConsumer.consume(event -> logger.info("got event: {}", event));
        } catch (Exception e) {
            logger.error("exiting program", e);
        }
    }
}
