package com.funnelsensai.core.dto.appointmentsForContact;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class AppointmentsForContact {
    @JsonProperty("id")
    private String id;
    @JsonProperty("calendarId")
    private String calendarId;
    @JsonProperty("status")
    private String status;
    @JsonProperty("title")
    private String title;
    @JsonProperty("appointmentStatus")
    private String appointmentStatus;
    @JsonProperty("assignedUserId")
    private String assignedUserId;
    @JsonProperty("notes")
    private String notes;
    @JsonProperty("startTime")
    private String startTime;
    @JsonProperty("endTime")
    private String endTime;
    @JsonProperty("address")
    private String address;
    @JsonProperty("locationId")
    private String locationId;
    @JsonProperty("contactId")
    private String contactId;
    @JsonProperty("groupId")
    private String groupId;
    @JsonProperty("users")
    private List<String> users;
    @JsonProperty("dateAdded")
    private String dateAdded;
    @JsonProperty("dateUpdated")
    private String dateUpdated;
    @JsonProperty("assignedResources")
    private List<String> assignedResources;

    public AppointmentsForContact() {
    }

    public AppointmentsForContact(String id, String calendarId, String status, String title,
                                  String appointmentStatus, String assignedUserId, String notes,
                                  String startTime, String endTime, String address, String locationId,
                                  String contactId, String groupId, List<String> users, String dateAdded,
                                  String dateUpdated, List<String> assignedResources) {
        this.id = id;
        this.calendarId = calendarId;
        this.status = status;
        this.title = title;
        this.appointmentStatus = appointmentStatus;
        this.assignedUserId = assignedUserId;
        this.notes = notes;
        this.startTime = startTime;
        this.endTime = endTime;
        this.address = address;
        this.locationId = locationId;
        this.contactId = contactId;
        this.groupId = groupId;
        this.users = users;
        this.dateAdded = dateAdded;
        this.dateUpdated = dateUpdated;
        this.assignedResources = assignedResources;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCalendarId() {
        return calendarId;
    }

    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public List<String> getAssignedResources() {
        return assignedResources;
    }

    public void setAssignedResources(List<String> assignedResources) {
        this.assignedResources = assignedResources;
    }

    @Override
    public String toString() {
        return "AppointmentsForContact{" +
                "id='" + id + '\'' +
                ", calendarId='" + calendarId + '\'' +
                ", status='" + status + '\'' +
                ", title='" + title + '\'' +
                ", appointmentStatus='" + appointmentStatus + '\'' +
                ", assignedUserId='" + assignedUserId + '\'' +
                ", notes='" + notes + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", address='" + address + '\'' +
                ", locationId='" + locationId + '\'' +
                ", contactId='" + contactId + '\'' +
                ", groupId='" + groupId + '\'' +
                ", users=" + users +
                ", dateAdded='" + dateAdded + '\'' +
                ", dateUpdated='" + dateUpdated + '\'' +
                ", assignedResources=" + assignedResources +
                '}';
    }
}