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
@Table(name = "DF_POSITION")
@Entity(name = "Position_")
public class Position {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @OneToMany(mappedBy = "position")
    private List<Employee> employees;

    @Temporal(TemporalType.DATE)
    @Column(name = "DELETE_TS")
    @SystemLevel
    private Date deleteTs;

    @Column(name = "UPDATED_BY", length = 50)
    @SystemLevel
    private String updatedBy;

    @Column(name = "CREATED_BY", length = 50)
    @SystemLevel
    private String createdBy;

    @InstanceName
    @Column(name = "NAME", length = 400)
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(name = "CREATE_TS")
    @SystemLevel
    private Date createTs;

    @Column(name = "VERSION")
    @SystemLevel
    @Version
    private Integer version;

    @Temporal(TemporalType.DATE)
    @Column(name = "UPDATE_TS")
    @SystemLevel
    private Date updateTs;

    @Column(name = "DELETED BY", length = 50)
    @SystemLevel
    private String deletedBy;

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public Date getUpdateTs() {
        return updateTs;
    }

    public void setUpdateTs(Date updateTs) {
        this.updateTs = updateTs;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getDeleteTs() {
        return deleteTs;
    }

    public void setDeleteTs(Date deleteTs) {
        this.deleteTs = deleteTs;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}