package com.moptra.hrms.model;

import lombok.Builder;

import java.util.Date;
import java.util.List;

public class AuthResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private Date expiresIn; // in seconds or milliseconds
    private String email;
    private List<String> roles;

    public AuthResponse(String accessToken, String tokenType, Date expiresIn, String email, List<String> roles) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.expiresIn = expiresIn;
        this.email = email;
        this.roles = roles;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Date getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Date expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "AuthResponse{" +
                "accessToken='" + accessToken + '\'' +
                ", tokenType='" + tokenType + '\'' +
                ", expiresIn=" + expiresIn +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                '}';
    }
}
