package com.funnelsensai.core.dto.calendarEventGetAppointment;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CalendarEventGetAppointmentResponse {
    @JsonProperty("event")
    private CalendarEventGetAppointment event;

    public CalendarEventGetAppointmentResponse() {}

    public CalendarEventGetAppointmentResponse(CalendarEventGetAppointment event) {
        this.event = event;
    }

    public CalendarEventGetAppointment getEvent() {
        return event;
    }

    public void setEvent(CalendarEventGetAppointment event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "CalendarEventGetAppointmentResponse{" +
                "event=" + event +
                '}';
    }
}