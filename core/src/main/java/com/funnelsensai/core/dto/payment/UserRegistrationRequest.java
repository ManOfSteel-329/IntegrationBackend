package com.funnelsensai.core.dto.payment;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRegistrationRequest {
    @NotBlank
    private String firstName;
    
    @NotBlank
    private String lastName;
    
    @NotBlank
    @Email
    private String email;
    
    private String companyName;
    
    @NotBlank
    private String password;
    
    // Address
    @NotBlank
    private String line1;
    
    @NotBlank
    private String city;
    
    @NotBlank
    private String state;
    
    @NotBlank
    private String postalCode;
    
    @NotBlank
    private String country;
} 