package com.example.organization.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class OrganizationDeleteDto {

    @JsonProperty
    private UUID registryNumber;
    @JsonProperty
    private String userEmail;

    public OrganizationDeleteDto() {
    }

    public OrganizationDeleteDto(UUID registryNumber, String userEmail) {
        this.registryNumber = registryNumber;
        this.userEmail = userEmail;
    }

    public UUID getRegistryNumber() {
        return registryNumber;
    }

    public void setRegistryNumber(UUID registryNumber) {
        this.registryNumber = registryNumber;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
