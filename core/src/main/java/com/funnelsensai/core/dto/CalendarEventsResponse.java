package com.funnelsensai.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class CalendarEventsResponse {

    @JsonProperty("events")
    private List<CalendarEvent> events;

    public List<CalendarEvent> getEvents() {
        return events;
    }

    public void setEvents(List<CalendarEvent> events) {
        this.events = events;
    }
}
