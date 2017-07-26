/*
 * (c) Copyright 2017 sothawo
 */
package com.sothawo.basy;

import com.sothawo.basy.events.Event;
import org.jetbrains.annotations.NotNull;

/**
 * @author P.J. Meisch (pj.meisch@sothawo.com)
 */
public interface EventStoreProducer extends AutoCloseable {
    void produce(@NotNull Event evt);
}
