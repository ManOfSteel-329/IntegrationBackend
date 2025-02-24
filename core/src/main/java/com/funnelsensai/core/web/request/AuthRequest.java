package com.funnelsensai.core.web.request;

public class AuthRequest {
    private String email;
    private String password;
    private String companyName;// Added here cause when user is created it needs to belong to a company

    // Getters and Setters


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
