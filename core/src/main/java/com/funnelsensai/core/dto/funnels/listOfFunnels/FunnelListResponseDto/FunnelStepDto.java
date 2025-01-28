package com.funnelsensai.core.dto.funnels.listOfFunnels.FunnelListResponseDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class FunnelStepDto {
    @JsonProperty("id")
    private String step_id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("originId")
    private String originId;

    @JsonProperty("pages")
    private List<String> pages;

    @JsonProperty("products")
    private List<String> products;

    @JsonProperty("sequence")
    private int sequence;

    @JsonProperty("type")
    private String type;

    @JsonProperty("url")
    private String url;

    public String getStep_id() {
        return step_id;
    }

    public void setStep_id(String step_id) {
        this.step_id = step_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginId() {
        return originId;
    }

    public void setOriginId(String originId) {
        this.originId = originId;
    }

    public List<String> getPages() {
        return pages;
    }

    public void setPages(List<String> pages) {
        this.pages = pages;
    }

    public List<String> getProducts() {
        return products;
    }

    public void setProducts(List<String> products) {
        this.products = products;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
