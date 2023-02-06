package com.example.organization.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.UUID;

public class OrganizationCreateDto {
    @JsonProperty
    private UUID registryNumber;

    @JsonProperty
    private String organizationName;

    @JsonProperty
    private String normalizedOrganizationName;

    @JsonProperty
    private String contactEmail;

    @JsonProperty
    private Integer yearFounded;

    @JsonProperty
    private String phone;

    @JsonProperty
    private Integer companySize;

    @JsonProperty
    private String userMail;

    @JsonProperty
    private Date createdDate;

    @JsonProperty
    private Date updatedDate;

    @JsonProperty
    private UUID createdBy;

    @JsonProperty
    private UUID updatedBy;

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UUID createdBy) {
        this.createdBy = createdBy;
    }

    public UUID getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(UUID updatedBy) {
        this.updatedBy = updatedBy;
    }

    public UUID getRegistryNumber() {
        return registryNumber;
    }

    public void setRegistryNumber(UUID registryNumber) {
        this.registryNumber = registryNumber;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getNormalizedOrganizationName() {
        return normalizedOrganizationName;
    }

    public void setNormalizedOrganizationName(String normalizedOrganizationName) {
        this.normalizedOrganizationName = normalizedOrganizationName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public Integer getYearFounded() {
        return yearFounded;
    }

    public void setYearFounded(Integer yearFounded) {
        this.yearFounded = yearFounded;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getCompanySize() {
        return companySize;
    }

    public void setCompanySize(Integer companySize) {
        this.companySize = companySize;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }
}
