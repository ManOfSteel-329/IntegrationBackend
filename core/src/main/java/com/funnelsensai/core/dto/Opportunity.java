package com.funnelsensai.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

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
    private List<String> notes;

    @JsonProperty("tasks")
    private List<String> tasks;

    @JsonProperty("calendarEvents")
    private List<String> calendarEvents;

    @JsonProperty("customFields")
    private List<CustomField> customFields;

    @JsonProperty("followers")
    private List<List<String>> followers;







}
