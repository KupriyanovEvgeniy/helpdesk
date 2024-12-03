package com.company.helpdesk.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;

import java.util.UUID;

@JmixEntity
@Table(name = "REPAIR_REQUEST", indexes = {
        @Index(name = "IDX_REPAIR_REQUEST_USER", columnList = "USER_ID"),
        @Index(name = "IDX_REPAIR_REQUEST_ROOM", columnList = "ROOM_ID"),
        @Index(name = "IDX_REPAIR_REQUEST_EQUIPMENT", columnList = "EQUIPMENT_ID"),
        @Index(name = "IDX_REPAIR_REQUEST_FAULT_TYPE", columnList = "FAULT_TYPE_ID"),
        @Index(name = "IDX_REPAIR_REQUEST_LOCATION", columnList = "LOCATION_ID")
})
@Entity
public class RepairRequest {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;
    @JoinColumn(name = "USER_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @JoinColumn(name = "LOCATION_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Location location;
    @JoinColumn(name = "ROOM_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Room room;
    @Column(name = "EQUIPMENT_TYPE")
    private String equipmentType;
    @JoinColumn(name = "EQUIPMENT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Equipment equipment;
    @JoinColumn(name = "FAULT_TYPE_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private FaultType faultType;
    @InstanceName
    @Column(name = "DESCRIPTION", length = 1000)
    private String description;

    public EquipmentType getEquipmentType() {
        return equipmentType == null ? null : EquipmentType.fromId(equipmentType);
    }

    public void setEquipmentType(EquipmentType equipmentType) {
        this.equipmentType = equipmentType == null ? null : equipmentType.getId();
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
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

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}