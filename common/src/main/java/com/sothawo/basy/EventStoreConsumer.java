/*
 * (c) Copyright 2017 sothawo
 */
package com.sothawo.basy;

import com.sothawo.basy.events.Event;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author P.J. Meisch (pj.meisch@sothawo.com)
 */
public interface EventStoreConsumer extends AutoCloseable {
    /**
     * starts listen to events, lets them process by the consumer and blocks until #close() is called.
     *
     * @param consumer
     *         the consumer processing the events
     */
    void consume(@NotNull Consumer<List<Event>> consumer);
}
