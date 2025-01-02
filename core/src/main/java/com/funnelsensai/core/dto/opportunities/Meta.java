package com.funnelsensai.core.dto.opportunities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Meta {

    @JsonProperty("total")
    private Integer total;

    @JsonProperty("nextPageUrl")
    private String nextPageUrl;

    @JsonProperty("startAfterId")
    private String startAfterId;

    @JsonProperty("startAfter")
    private Integer startAfter;

    @JsonProperty("currentPage")
    private Integer currentPage;

    @JsonProperty("nextPage")
    private Integer nextPage;

    @JsonProperty("prevPage")
    private Integer prevPage;





}
