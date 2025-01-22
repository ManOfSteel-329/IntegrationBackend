package com.funnelsensai.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SearchOpportunityRequest {

    private String location_id;
    private String assigned_to;
    private String campaignId;
    private String contact_id;
    private String country;
    private String date;
    private String endDate;
    private boolean getCalendarEvents;
    private boolean getNotes;
    private boolean getTasks;
    private String id;
    private Integer limit;
    private String order;
    private Integer page;
    private String pipeline_id;
    private String pipeline_stage_id;
    private String q;
    private String startAfter;
    private String startAfterId;
    private String status;


    public String getLocation_id() {
        return location_id;
    }

    public void setLocation_id(String location_id) {
        this.location_id = location_id;
    }

    public String getAssigned_to() {
        return assigned_to;
    }

    public void setAssigned_to(String assigned_to) {
        this.assigned_to = assigned_to;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public String getContact_id() {
        return contact_id;
    }

    public void setContact_id(String contact_id) {
        this.contact_id = contact_id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public boolean isGetCalendarEvents() {
        return getCalendarEvents;
    }

    public void setGetCalendarEvents(boolean getCalendarEvents) {
        this.getCalendarEvents = getCalendarEvents;
    }

    public boolean isGetNotes() {
        return getNotes;
    }

    public void setGetNotes(boolean getNotes) {
        this.getNotes = getNotes;
    }

    public boolean isGetTasks() {
        return getTasks;
    }

    public void setGetTasks(boolean getTasks) {
        this.getTasks = getTasks;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getPipeline_id() {
        return pipeline_id;
    }

    public void setPipeline_id(String pipeline_id) {
        this.pipeline_id = pipeline_id;
    }

    public String getPipeline_stage_id() {
        return pipeline_stage_id;
    }

    public void setPipeline_stage_id(String pipeline_stage_id) {
        this.pipeline_stage_id = pipeline_stage_id;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getStartAfter() {
        return startAfter;
    }

    public void setStartAfter(String startAfter) {
        this.startAfter = startAfter;
    }

    public String getStartAfterId() {
        return startAfterId;
    }

    public void setStartAfterId(String startAfterId) {
        this.startAfterId = startAfterId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
