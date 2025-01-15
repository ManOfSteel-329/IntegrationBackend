package com.funnelsensai.core.dto.CalendarEvent;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;
import java.util.List;

public class CalendarEvent {

    @JsonProperty("id")
    private String id;
    @JsonProperty("address")
    private String address;
    @JsonProperty("title")
    private String title;
    @JsonProperty("calendarId")
    private String calendarId;
    @JsonProperty("locationId")
    private String locationId;
    @JsonProperty("contactId")
    private String contactId;
    @JsonProperty("groupId")
    private String groupId;
    @JsonProperty("appointmentStatus")
    private String appointmentStatus;
    @JsonProperty("assignedUserId")
    private String assignedUserId;
    @JsonProperty("users")
    private List<String> users;
    @JsonProperty("notes")
    private String notes;
    @JsonProperty("isRecurring")
    private String isRecurring;
    @JsonProperty("rrule")
    private String rrule;
    @JsonProperty("startTime")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private ZonedDateTime startTime;
    @JsonProperty("endTime")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private ZonedDateTime endTime;
    @JsonProperty("dateAdded")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private ZonedDateTime dateAdded;
    @JsonProperty("dateUpdated")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private ZonedDateTime dateUpdated;
    @JsonProperty("assignedResources")
    private List<String> assignedResources;
    @JsonProperty("masterEventId")
    private String masterEventId;

    public CalendarEvent() {

    }

    public CalendarEvent(String id, String address, String title, String calendarId, String locationId, String contactId,
                         String groupId, String appointmentStatus, String assignedUserId, List<String> users, String notes, String isRecurring,
                         String rrule, ZonedDateTime startTime, ZonedDateTime endTime, ZonedDateTime dateAdded, ZonedDateTime dateUpdated,
                         List<String> assignedResources, String masterEventId) {
        this.id = id;
        this.address = address;
        this.title = title;
        this.calendarId = calendarId;
        this.locationId = locationId;
        this.contactId = contactId;
        this.groupId = groupId;
        this.appointmentStatus = appointmentStatus;
        this.assignedUserId = assignedUserId;
        this.users = users;
        this.notes = notes;
        this.isRecurring = isRecurring;
        this.rrule = rrule;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dateAdded = dateAdded;
        this.dateUpdated = dateUpdated;
        this.assignedResources = assignedResources;
        this.masterEventId = masterEventId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCalendarId() {
        return calendarId;
    }

    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(String appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public String getAssignedUserId() {
        return assignedUserId;
    }

    public void setAssignedUserId(String assignedUserId) {
        this.assignedUserId = assignedUserId;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String isRecurring() {
        return isRecurring;
    }

    public void setRecurring(String recurring) {
        isRecurring = recurring;
    }

    public String getRrule() {
        return rrule;
    }

    public void setRrule(String rrule) {
        this.rrule = rrule;
    }

    public ZonedDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(ZonedDateTime startTime) {
        this.startTime = startTime;
    }

    public ZonedDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(ZonedDateTime endTime) {
        this.endTime = endTime;
    }

    public ZonedDateTime getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(ZonedDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }

    public ZonedDateTime getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(ZonedDateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public List<String> getAssignedResources() {
        return assignedResources;
    }

    public void setAssignedResources(List<String> assignedResources) {
        this.assignedResources = assignedResources;
    }

    public String getMasterEventId() {
        return masterEventId;
    }

    public void setMasterEventId(String masterEventId) {
        this.masterEventId = masterEventId;
    }

    @Override
    public String toString() {
        return "CalendarEvent{" +
                "id='" + id + '\'' +
                ", address='" + address + '\'' +
                ", title='" + title + '\'' +
                ", calendarId='" + calendarId + '\'' +
                ", locationId='" + locationId + '\'' +
                ", contactId='" + contactId + '\'' +
                ", groupId='" + groupId + '\'' +
                ", appointmentStatus='" + appointmentStatus + '\'' +
                ", assignedUserId='" + assignedUserId + '\'' +
                ", users=" + users +
                ", notes='" + notes + '\'' +
                ", isRecurring=" + isRecurring +
                ", rrule='" + rrule + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", dateAdded=" + dateAdded +
                ", dateUpdated=" + dateUpdated +
                ", assignedResources=" + assignedResources +
                ", masterEventId='" + masterEventId + '\'' +
                '}';
    }
}
