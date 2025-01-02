package com.funnelsensai.core.dto.opportunities;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.funnelsensai.core.dto.opportunities.util.FieldValueDeserializer;

public class CustomField {

    @JsonProperty("id")
    private String id;

    @JsonProperty("fieldValue")
    @JsonDeserialize(using = FieldValueDeserializer.class)
    private Object fieldValue;


}
