package com.funnelsensai.core.dto.funnels.listOfFunnels.FunnelListResponseDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class FunnelResponseDto {
    //Response object return a Funnel object with count and traceid attributes

    @JsonProperty("funnels")
    private FunnelDto funnel;

    @JsonProperty("count")
    private int count;

    @JsonProperty("traceId")
    private String traceId;

    public FunnelDto getFunnel() {
        return funnel;
    }

    public void setFunnel(FunnelDto funnel) {
        this.funnel = funnel;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }
}
