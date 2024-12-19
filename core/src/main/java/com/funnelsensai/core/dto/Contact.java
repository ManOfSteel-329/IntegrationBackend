package com.funnelsensai.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Contact {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("companyName")
    private String companyName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("tags")
    private List<String> tags;



}
