/*
 * (c) Copyright 2017 sothawo
 */
package com.sothawo.basy;

import com.sothawo.basy.events.Event;
import org.jetbrains.annotations.NotNull;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

/**
 * @author P.J. Meisch (pj.meisch@sothawo.com)
 */
public class TableEvent {

    private static DateTimeFormatter formatter =
            DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                    .withLocale(Locale.GERMANY)
                    .withZone(ZoneId.of("Europe/Berlin"));

    private String creationTime;
    private String eventClass;
    private String description;

    public TableEvent(@NotNull Event event) {
        this.creationTime = formatter.format(event.getCreationTime());
        this.eventClass = event.getClass().getSimpleName();
        this.description = event.getDescription();
    }

    public String getCreationTime() {
        return creationTime;
    }

    public String getEventClass() {
        return eventClass;
    }

    public String getDescription() {
        return description;
    }
}
