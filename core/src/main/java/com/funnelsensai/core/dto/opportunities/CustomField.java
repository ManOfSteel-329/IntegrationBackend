package com.funnelsensai.core.dto.opportunities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.funnelsensai.core.dto.opportunities.util.FieldValueDeserializer;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomField {

    @JsonProperty("id")
    private String id;

    @JsonProperty("fieldValue")
    @JsonDeserialize(using = FieldValueDeserializer.class)
    private Object fieldValue;

    public CustomField() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(Object fieldValue) {
        this.fieldValue = fieldValue;
    }
}
