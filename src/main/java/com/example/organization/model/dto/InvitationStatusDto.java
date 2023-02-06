package com.example.organization.model.dto;

import com.example.organization.model.InvitationStatus;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class InvitationStatusDto {

    private String organizationEmail;
    private String userEmail;
    @Enumerated(EnumType.STRING)
    private InvitationStatus status;

    public String getOrganizationEmail() {
        return organizationEmail;
    }

    public void setOrganizationEmail(String organizationEmail) {
        this.organizationEmail = organizationEmail;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public InvitationStatus getStatus() {
        return status;
    }

    public void setStatus(InvitationStatus status) {
        this.status = status;
    }
}
