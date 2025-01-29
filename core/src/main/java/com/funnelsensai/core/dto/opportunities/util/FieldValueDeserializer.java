package com.funnelsensai.core.dto.opportunities.util;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class FieldValueDeserializer extends JsonDeserializer<Object> {

    @Override
    public Object deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        switch (p.getCurrentToken()) {
            case VALUE_STRING:
                return p.getText();
            case VALUE_NUMBER_INT:
                return p.getLongValue();
            case VALUE_NUMBER_FLOAT:
                return p.getDoubleValue();
            case VALUE_TRUE:
            case VALUE_FALSE:
                return p.getBooleanValue();
            case VALUE_NULL:
                return null;
            default:
                // For complex objects or arrays, read them as a generic Object
                return p.readValueAs(Object.class);
        }
    }
}
