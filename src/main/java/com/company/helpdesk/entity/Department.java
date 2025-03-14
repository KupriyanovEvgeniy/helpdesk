package com.company.helpdesk.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.SystemLevel;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@JmixEntity
@Table(name = "DF_DEPARTMENT", indexes = {
        @Index(name = "IDX_DF_DEPARTMENT_PARENT_DEPARTMENT", columnList = "PARENT_DEPARTMENT_ID"),
        @Index(name = "IDX_DF_DEPARTMENT_ORGANIZATION", columnList = "ORGANIZATION_ID")
})
@Entity
public class Department {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @JoinColumn(name = "ORGANIZATION_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Organization organization;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees;

    @Column(name = "UPDATED_BY", length = 50)
    @SystemLevel
    private String updatedBy;

    @Column(name = "CODE", length = 20)
    private String code;

    @JoinColumn(name = "PARENT_DEPARTMENT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Department parentDepartment;

    @Column(name = "VERSION")
    @SystemLevel
    @Version
    private Integer version;

    @Column(name = "DELETED_BY", length = 50)
    @SystemLevel
    private String deletedBy;

    @Temporal(TemporalType.DATE)
    @Column(name = "DELETE_TS")
    @SystemLevel
    private Date deleteTs;

    @Column(name = "CREATED_BY", length = 50)
    @SystemLevel
    private String createdBy;

    @InstanceName
    @Column(name = "NAME")
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(name = "CREATE_TS")
    @SystemLevel
    private Date createTs;

    @Column(name = "HAS_ATTACHMENTS")
    private Boolean hasAttachments;

    @Temporal(TemporalType.DATE)
    @Column(name = "UPDATE_TS")
    @SystemLevel
    private Date updateTs;

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Date getUpdateTs() {
        return updateTs;
    }

    public void setUpdateTs(Date updateTs) {
        this.updateTs = updateTs;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Department getParentDepartment() {
        return parentDepartment;
    }

    public void setParentDepartment(Department parentDepartment) {
        this.parentDepartment = parentDepartment;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}