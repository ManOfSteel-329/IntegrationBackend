package com.funnelsensai.core.dto;

import com.funnelsensai.core.domain.Company;

public class CompanyResponseDTO {
    private String message;
    private Company company;

    public CompanyResponseDTO(String message) {
        this.message = message;
    }

    public CompanyResponseDTO(Company company) {
        this.company = company;
    }

    public String getMessage() {
        return message;
    }

    public Company getCompany() {
        return company;
    }
}
