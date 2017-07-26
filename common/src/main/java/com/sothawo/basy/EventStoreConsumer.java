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
    void consume(Consumer<Event> consumer);
}
