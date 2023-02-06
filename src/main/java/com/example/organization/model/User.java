package com.example.organization.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @Column(nullable = false)
    @GeneratedValue
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    private UUID id;

    @Column(nullable = false)
    @JsonProperty
    private String fullName;

    @Column(nullable = false, unique = true)
    @JsonProperty
    private String email;

    @Column(nullable = false)
    @JsonProperty
    private String normalizedName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @JsonProperty
    private UserStatus status;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date createdDate;

    @Temporal(TemporalType.DATE)
    private Date updatedDate;

    @Column(nullable = false)
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    private UUID createdBy;

    @Column()
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    private UUID updatedBy;

    @ManyToMany()
    @JoinTable(
            name = "organization_users",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "organization_id")
    )
    @JsonIgnore
    private Set<Organization> organization;


    public Set<Organization> getOrganization() {
        return organization;
    }

    public void setOrganization(Set<Organization> organization) {
        this.organization = organization;
    }

    public User() {

    }

    public User(String fullName, String email, String normalizedName, UserStatus status, UUID createdBy) {
        this.fullName = fullName;
        this.email = email;
        this.normalizedName = normalizedName;
        this.status = status;
        this.createdBy = createdBy;
    }

    @PrePersist
    protected void onCreate() {
        createdDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedDate = new Date();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNormalizedName() {
        return normalizedName;
    }

    public void setNormalizedName(String normalizedName) {
        this.normalizedName = normalizedName;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

// other fields, getters and setters


}

