package com.funnelsensai.core.dto.opportunities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Opportunity {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("monetaryValue")
    private Integer monetaryValue;

    @JsonProperty("pipelineId")
    private String pipelineId;

    @JsonProperty("pipelineStageId")
    private String pipelineStageId;

    @JsonProperty("assignedTo")
    private String assignedTo;

    @JsonProperty("status")
    private String status;

    @JsonProperty("source")
    private String source;

    @JsonProperty("lastStatusChangeAt")
    private String lastStatusChangeAt;

    @JsonProperty("lastStageChangeAt")
    private String lastStageChangeAt;

    @JsonProperty("lastActionDate")
    private String lastActionDate;

    @JsonProperty("indexVersion")
    private String indexVersion;

    @JsonProperty("createdAt")
    private String createdAt;

    @JsonProperty("updatedAt")
    private String updatedAt;

    @JsonProperty("contactId")
    private String contactId;

    @JsonProperty("locationId")
    private String locationId;

    @JsonProperty("contact")
    private Contact contact;

    @JsonProperty("notes")
    private List<String> notes = new ArrayList<>();

    @JsonProperty("tasks")
    private List<String> tasks = new ArrayList<>();

    @JsonProperty("calendarEvents")
    private List<String> calendarEvents = new ArrayList<>();

    @JsonProperty("customFields")
    private List<CustomField> customFields = new ArrayList<>();

    @JsonProperty("followers")
    private List<List<String>> followers = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMonetaryValue() {
        return monetaryValue;
    }

    public void setMonetaryValue(Integer monetaryValue) {
        this.monetaryValue = monetaryValue;
    }

    public String getPipelineId() {
        return pipelineId;
    }

    public void setPipelineId(String pipelineId) {
        this.pipelineId = pipelineId;
    }

    public String getPipelineStageId() {
        return pipelineStageId;
    }

    public void setPipelineStageId(String pipelineStageId) {
        this.pipelineStageId = pipelineStageId;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getLastStatusChangeAt() {
        return lastStatusChangeAt;
    }

    public void setLastStatusChangeAt(String lastStatusChangeAt) {
        this.lastStatusChangeAt = lastStatusChangeAt;
    }

    public String getLastStageChangeAt() {
        return lastStageChangeAt;
    }

    public void setLastStageChangeAt(String lastStageChangeAt) {
        this.lastStageChangeAt = lastStageChangeAt;
    }

    public String getLastActionDate() {
        return lastActionDate;
    }

    public void setLastActionDate(String lastActionDate) {
        this.lastActionDate = lastActionDate;
    }

    public String getIndexVersion() {
        return indexVersion;
    }

    public void setIndexVersion(String indexVersion) {
        this.indexVersion = indexVersion;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public List<String> getNotes() {
        return notes;
    }

    public void setNotes(List<String> notes) {
        this.notes = notes;
    }

    public List<String> getTasks() {
        return tasks;
    }

    public void setTasks(List<String> tasks) {
        this.tasks = tasks;
    }

    public List<String> getCalendarEvents() {
        return calendarEvents;
    }

    public void setCalendarEvents(List<String> calendarEvents) {
        this.calendarEvents = calendarEvents;
    }

    public List<CustomField> getCustomFields() {
        return customFields;
    }

    public void setCustomFields(List<CustomField> customFields) {
        this.customFields = customFields;
    }

    public List<List<String>> getFollowers() {
        return followers;
    }

    public void setFollowers(List<List<String>> followers) {
        this.followers = followers;
    }

}
