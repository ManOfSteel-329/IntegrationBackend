package com.funnelsensai.core.dto.conversations;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@SuperBuilder
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

}
