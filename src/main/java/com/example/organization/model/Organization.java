package com.example.organization.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Entity
@Table(name = "organization")
public class Organization {

    @Id
    @Column(nullable = false)
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    @GeneratedValue
    @JsonProperty
    private UUID registryNumber;

    @Column(nullable = false)
    @JsonProperty
    private String organizationName;

    @Column(nullable = false)
    @JsonProperty
    private String normalizedOrganizationName;

    @Column(nullable = false)
    @JsonProperty
    private String contactEmail;

    @Column(nullable = false)
    @JsonProperty
    private Integer yearFounded;

    @Column(nullable = false)
    @JsonProperty
    private String phone;

    @Column(nullable = false)
    @JsonProperty
    private Integer companySize;

    @ManyToMany(mappedBy = "organization")
    @JsonIgnore
    private Set<User> users = new HashSet<>();


    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date createdDate;

    @Temporal(TemporalType.DATE)
    private Date updatedDate;

    @Column(nullable = false)
    @org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
    private UUID createdBy;

    @Column()
    @org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
    private UUID updatedBy;

    @PrePersist
    protected void onCreate() {
        createdDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedDate = new Date();
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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

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

    public Organization(UUID registryNumber, String organizationName, String normalizedOrganizationName, String contactEmail, Integer yearFounded, String phone, Integer companySize, Set<User> users, Date createdDate, Date updatedDate, UUID createdBy, UUID updatedBy) {
        this.registryNumber = registryNumber;
        this.organizationName = organizationName;
        this.normalizedOrganizationName = normalizedOrganizationName;
        this.contactEmail = contactEmail;
        this.yearFounded = yearFounded;
        this.phone = phone;
        this.companySize = companySize;
        this.users = users;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }

    public Organization() {

    }
}
