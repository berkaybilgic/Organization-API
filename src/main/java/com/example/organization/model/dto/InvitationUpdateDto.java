package com.example.organization.model.dto;

import com.example.organization.model.InvitationStatus;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.UUID;

public class InvitationUpdateDto {

    private UUID id;
    private String invitationMessage;
    @Enumerated(EnumType.STRING)
    private InvitationStatus status;
    private String organizationEmail;
    private String userEmail;
    private UUID updatedUser;

    public UUID getUpdatedUser() {
        return updatedUser;
    }

    public void setUpdatedUser(UUID updatedUser) {
        this.updatedUser = updatedUser;
    }

    public String getInvitationMessage() {
        return invitationMessage;
    }

    public void setInvitationMessage(String invitationMessage) {
        this.invitationMessage = invitationMessage;
    }

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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

}
