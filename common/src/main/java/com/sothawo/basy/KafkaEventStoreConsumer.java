/*
 * (c) Copyright 2017 sothawo
 */
package com.sothawo.basy;

import com.sothawo.basy.events.Event;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author P.J. Meisch (pj.meisch@sothawo.com)
 */
public class KafkaEventStoreConsumer implements EventStoreConsumer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaEventStoreConsumer.class);

    private final KafkaConsumer<String, Event> kafkaConsumer;
    private final AtomicBoolean running = new AtomicBoolean(false);
    private final String topic;

    public KafkaEventStoreConsumer(@NotNull Properties props, @NotNull String topic) {
        this.kafkaConsumer = new KafkaConsumer<>(props);
        this.topic = topic;
    }

    @Override
    public void consume(@NotNull Consumer<List<Event>> consumer) {
        running.set(true);
        final Thread thread = new Thread(() -> {
            kafkaConsumer.subscribe(Collections.singletonList(topic));
            try {
                while (running.get()) {
                    consumer.accept(StreamSupport.stream(
                            kafkaConsumer.poll(Long.MAX_VALUE).spliterator(), false)
                            .map(ConsumerRecord::value)
                            .collect(Collectors.toList()));
                }
            } catch (WakeupException e) {
                if (running.get()) {
                    throw e;
                }
            } finally {
                kafkaConsumer.close();
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            logger.error("joining consumer thread: ", e);
        }
    }

    @Override
    public void close() throws Exception {
        if (running.getAndSet(false)) {
            kafkaConsumer.wakeup();
        }
    }
}
