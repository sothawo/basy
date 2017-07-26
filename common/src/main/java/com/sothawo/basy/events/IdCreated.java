/*
 * (c) Copyright 2017 sothawo
 */
package com.sothawo.basy.events;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * @author P.J. Meisch (pj.meisch@sothawo.com)
 */
public class IdCreated extends Event {
    private String id;

    private IdCreated() {
    }

    public IdCreated(@NotNull String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        IdCreated idCreated = (IdCreated) o;
        return Objects.equals(id, idCreated.id);
    }

    @Override
    public String toString() {
        return "IdCreated{" +
                "id='" + id + '\'' +
                "} " + super.toString();
    }
}
