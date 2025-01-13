package com.funnelsensai.core.dto.funnels.listOfFunnels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@JsonIgnoreProperties(ignoreUnknown = true)
public class FunnelPagesCountDto {

    private float count;

    public float getCount() {
        return count;
    }

    public void setCount(float count) {
        this.count = count;
    }
}
