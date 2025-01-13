package com.funnelsensai.core.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "user_conversation")
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contactId;
    private String locationId;
    private Boolean deleted;
    private Boolean inbox;
    private Float type;
    private Float unreadCount;
    private String assignedTo;
    private String conversationId;
    private String starred;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
