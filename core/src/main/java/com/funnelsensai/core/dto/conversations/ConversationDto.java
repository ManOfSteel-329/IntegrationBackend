package com.funnelsensai.core.dto.conversations;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ConversationDto {
    // The values for this Dto can be generated statically or dynamically

    private String contactId;
    private String locationId;
    private Boolean deleted;
    private Boolean inbox;
    private Float type;
    private Float unreadCount;
    private String assignedTo;
    @JsonProperty("id")
    private String conversationId;
    private String starred;

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

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Boolean getInbox() {
        return inbox;
    }

    public void setInbox(Boolean inbox) {
        this.inbox = inbox;
    }

    public Float getType() {
        return type;
    }

    public void setType(Float type) {
        this.type = type;
    }

    public Float getUnreadCount() {
        return unreadCount;
    }

    public void setUnreadCount(Float unreadCount) {
        this.unreadCount = unreadCount;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public String getStarred() {
        return starred;
    }

    public void setStarred(String starred) {
        this.starred = starred;
    }
}
