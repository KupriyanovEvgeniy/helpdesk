package com.company.helpdesk.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.SystemLevel;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.util.Date;
import java.util.UUID;

@JmixEntity
@Table(name = "DF_EMPLOYEE", indexes = {
        @Index(name = "IDX_DF_EMPLOYEE_DEPARTMENT", columnList = "DEPARTMENT_ID"),
        @Index(name = "IDX_DF_EMPLOYEE_ORGANIZATION", columnList = "ORGANIZATION_ID"),
        @Index(name = "IDX_DF_EMPLOYEE_POSITION", columnList = "POSITION_ID"),
        @Index(name = "IDX_DF_EMPLOYEE_USER", columnList = "USER_ID")
})
@Entity
public class Employee {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Temporal(TemporalType.DATE)
    @Column(name = "BIRTHDAY")
    private Date birthday;

    @Column(name = "EMD_CERTIFICATE_THUMBPRINT")
    private String emdCertificateThumbprint;

    @InstanceName
    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "DELETED_BY", length = 50)
    @SystemLevel
    private String deletedBy;

    @Temporal(TemporalType.DATE)
    @Column(name = "DELETE_TS")
    @SystemLevel
    private Date deleteTs;

    @Column(name = "NUMBER_", length = 50)
    private String number;

    @Temporal(TemporalType.DATE)
    @Column(name = "CREATE_TS")
    @SystemLevel
    private Date createTs;

    @Column(name = "HAS_ATTACHMENTS")
    private Boolean hasAttachments;

    @Column(name = "FAX", length = 100)
    private String fax;

    @JoinColumn(name = "DEPARTMENT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;

    @Column(name = "EMAIL", length = 100)
    @Email
    private String email;

    @Column(name = "PERSONAL_DATA_ID")
    @SystemLevel(value = true, propagateToSubclasses = true)
    private UUID personalDataId;

    @Column(name = "UPDATED_BY", length = 50)
    @SystemLevel
    private String updatedBy;

    @Column(name = "VERSION")
    @SystemLevel
    @Version
    private Integer version;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "MOBILE_PHONE", length = 100)
    private String mobilePhone;

    @Column(name = "CREATED_BY", length = 50)
    @SystemLevel
    private String createdBy;

    @Column(name = "PHONE", length = 100)
    private String phone;

    @JoinColumn(name = "ORGANIZATION_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Organization organization;

    @Column(name = "NAME")
    private String name;

    @Column(name = "MIDDLE_NAME")
    private String middleName;

    @Column(name = "COMMENT_", length = 1000)
    private String comment;

    @JoinColumn(name = "POSITION_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Position position;

    @Temporal(TemporalType.DATE)
    @Column(name = "UPDATE_TS")
    @SystemLevel
    private Date updateTs;

    @JoinColumn(name = "USER_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getUpdateTs() {
        return updateTs;
    }

    public void setUpdateTs(Date updateTs) {
        this.updateTs = updateTs;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public UUID getPersonalDataId() {
        return personalDataId;
    }

    public void setPersonalDataId(UUID personalDataId) {
        this.personalDataId = personalDataId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public Boolean getHasAttachments() {
        return hasAttachments;
    }

    public void setHasAttachments(Boolean hasAttachments) {
        this.hasAttachments = hasAttachments;
    }

    public Date getCreateTs() {
        return createTs;
    }

    public void setCreateTs(Date createTs) {
        this.createTs = createTs;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getDeleteTs() {
        return deleteTs;
    }

    public void setDeleteTs(Date deleteTs) {
        this.deleteTs = deleteTs;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmdCertificateThumbprint() {
        return emdCertificateThumbprint;
    }

    public void setEmdCertificateThumbprint(String emdCertificateThumbprint) {
        this.emdCertificateThumbprint = emdCertificateThumbprint;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}