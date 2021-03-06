/*
 * (c) Copyright 2017 sothawo
 */
package com.sothawo.basy;

import com.sothawo.basy.events.IdCreated;
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

    public static void main(String[] args) {
        new IdProducer().run();
    }

    private void run() {
        final Properties props = new KafkaProperties();

        try (EventStoreProducer eventStoreProducer = new KafkaEventStoreProducer(props, KafkaTopics.ACCOUNT_EVENTS)) {
            //noinspection InfiniteLoopStatement
            while (true) {
                final IdCreated event = new IdCreated(UUID.randomUUID().toString());
                logger.info("created event: {}", event);
                eventStoreProducer.produce(event);

                Thread.sleep(SLEEP_TIME);
            }
        } catch (Exception e) {
            logger.error("exiting program", e);
        }

    }
}
