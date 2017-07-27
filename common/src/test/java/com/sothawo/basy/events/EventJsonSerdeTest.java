/*
 * (c) Copyright 2017 sothawo
 */
package com.sothawo.basy.events;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author P.J. Meisch (pj.meisch@sothawo.com)
 */
public class EventJsonSerdeTest {
    @Test
    public void serializeAndDeserialize() throws Exception {
        final Event testEvent = new TestEvent("this is a test");

        final EventJsonSerde serde = new EventJsonSerde();
        final String json = serde.serialize(testEvent);
        final Event event = serde.deserialize(json);

        assertThat(event).isEqualTo(testEvent);
    }

    public static class TestEvent extends Event {
        private final String message;

        private TestEvent() {
            this("");
        }

        public TestEvent(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        @Override
        public int hashCode() {
            return Objects.hash(super.hashCode(), message);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            TestEvent testEvent = (TestEvent) o;
            return Objects.equals(message, testEvent.message);
        }

        @Override
        public String toString() {
            return "TestEvent{" +
                    "message='" + message + '\'' +
                    "} " + super.toString();
        }

        @Override
        public @NotNull String getDescription() {
            return message;
        }


    }

}
