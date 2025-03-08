package com.funnelsensai.core.dto.ResponseDto;

import com.funnelsensai.core.domain.Company;
import com.funnelsensai.core.domain.User;

import java.util.List;

public class CompanyResponseDTO {

    private Long id;
    private String name;
    private List<String> users; // Store only user names to avoid exposing full User objects.

    // Default constructor
    public CompanyResponseDTO() {}

    // Constructor for success responses
    public CompanyResponseDTO(Company company) {
        this.id = company.getId();
        this.name = company.getName();
        this.users = company.getUsers().stream().map(User::getUsername).toList(); // Adjust based on User entity
    }

    // Constructor for error handling (when company is not found)
    public CompanyResponseDTO(Long id, String name, List<String> users) {
        this.id = id;
        this.name = name;
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }
}
