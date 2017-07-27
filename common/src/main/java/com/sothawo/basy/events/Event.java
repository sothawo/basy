/*
 * (c) Copyright 2017 sothawo
 */
package com.sothawo.basy.events;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.util.Objects;

/**
 * @author P.J. Meisch (pj.meisch@sothawo.com)
 */
public abstract class Event {
    protected final Instant creationTime = Instant.now();

    public Instant getCreationTime() {
        return creationTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(creationTime);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(creationTime, event.creationTime);
    }

    @Override
    public String toString() {
        return "Event{" +
                "creationTime=" + creationTime +
                '}';
    }

    @NotNull
    @JsonIgnore
    abstract public String getDescription();
}
