package com.funnelsensai.core.dto.ResponseDto;

public class UserResponseDTO {
    private Long id;
    private String email;
    private String companyName;
    private String firstName;
    private String lastName;

//    public UserResponseDTO(Long id, String username, String companyName) {
//        this.id = id;
//        this.email = username;
//        this.companyName = companyName;
//    }

    public UserResponseDTO(Long id, String email, String name, String firstName, String lastName) {
        this.id = id;
        this.email = email;
        this.companyName = name;
        this.firstName= firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
