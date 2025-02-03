package com.funnelsensai.core.dto.ResponseDto;

public class UserResponseDTO {
    private Long id;
    private String username;
    private String companyName;

    public UserResponseDTO(Long id, String username, String companyName) {
        this.id = id;
        this.username = username;
        this.companyName = companyName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
