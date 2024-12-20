package com.funnelsensai.core.DTO;


import com.funnelsensai.core.domain.User;

import java.time.LocalDateTime;
import java.util.List;

public class CalendarEvent {

    private String id;
    private String address;
    private String title;
    private String calendarId;
    private String locationId;
    private String contactId;
    private String groupId;
    private String appointmentStatus;
    private String assignedUserId;
    private List<User> users;
    private String notes;
    private boolean isRecurring;
    private String rrule;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime dateAdded;
    private LocalDateTime dateUpdated;
    private List<String> assignedResources;
    private String masterEventId;

}
