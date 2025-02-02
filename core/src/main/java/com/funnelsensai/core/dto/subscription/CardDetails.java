package com.funnelsensai.core.dto.subscription;
import lombok.Data;

@Data
public class CardDetails {
    private String number;
    private Integer expMonth;
    private Integer expYear;
    private String cvc;
}