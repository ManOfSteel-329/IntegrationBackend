package com.funnelsensai.core.dto.appointmentsForContact;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class AppointmentsForContactResponse {
    @JsonProperty("events")
    private List<AppointmentsForContact> appointments;

    public AppointmentsForContactResponse() {
    }

    public AppointmentsForContactResponse(List<AppointmentsForContact> appointments) {
        this.appointments = appointments;
    }

    public List<AppointmentsForContact> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<AppointmentsForContact> appointments) {
        this.appointments = appointments;
    }

    @Override
    public String toString() {
        return "AppointmentsForContactResponse{" +
                "appointments=" + appointments +
                '}';
    }
}