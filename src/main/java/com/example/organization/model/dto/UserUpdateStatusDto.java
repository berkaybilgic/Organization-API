package com.example.organization.model.dto;

import com.example.organization.model.UserStatus;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class UserUpdateStatusDto {

    private String email;
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}
