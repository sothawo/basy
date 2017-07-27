/*
 * (c) Copyright 2017 sothawo
 */
package com.sothawo.basy;

import com.sothawo.basy.events.Event;

import java.util.function.Consumer;

/**
 * @author P.J. Meisch (pj.meisch@sothawo.com)
 */
public interface EventStoreConsumer extends AutoCloseable {
    /**
     * starts to events, lets them process by the consumer and blocks until #close() is called.
     * @param consumer
     */
    void consume(Consumer<Event> consumer);
}
