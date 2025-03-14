package com.company.helpdesk.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;

import java.util.UUID;

@JmixEntity
@Table(name = "REPAIR_REQUEST_EQUIPMENT", indexes = {
        @Index(name = "IDX_REPAIR_REQUEST_EQUIPMENT_QUIPMENT", columnList = "QUIPMENT_ID"),
        @Index(name = "IDX_REPAIR_REQUEST_EQUIPMENT_FAULT_TYPE", columnList = "FAULT_TYPE_ID"),
        @Index(name = "IDX_REPAIR_REQUEST_EQUIPMENT_REPAIR_REUEST", columnList = "REPAIR_REUEST_ID")
})
@Entity
public class RepairRequestEquipment {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;
    @Column(name = "EUIPMENT_TYPE")
    private String euipmentType;
    @JoinColumn(name = "QUIPMENT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Equipment equipment;
    @JoinColumn(name = "FAULT_TYPE_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private FaultType faultType;
    @InstanceName
    @Column(name = "DESCRIPTION")
    private String description;
    @JoinColumn(name = "REPAIR_REUEST_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private RepairRequest repairRequest;

    public RepairRequest getRepairRequest() {
        return repairRequest;
    }

    public void setRepairRequest(RepairRequest repairRequest) {
        this.repairRequest = repairRequest;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public FaultType getFaultType() {
        return faultType;
    }

    public void setFaultType(FaultType faultType) {
        this.faultType = faultType;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public EquipmentType getEuipmentType() {
        return euipmentType == null ? null : EquipmentType.fromId(euipmentType);
    }

    public void setEuipmentType(EquipmentType euipmentType) {
        this.euipmentType = euipmentType == null ? null : euipmentType.getId();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }


}