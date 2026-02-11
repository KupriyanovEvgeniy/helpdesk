package com.company.helpdesk.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@JmixEntity
@Table(name = "REPAIR_REQUEST_HISTORY", indexes = {
        @Index(name = "IDX_REPAIR_REQUEST_HISTORY_USER", columnList = "USER_ID"),
        @Index(name = "IDX_REPAIR_REQUEST_HISTORY_ROOM", columnList = "ROOM_ID"),
        @Index(name = "IDX_REPAIR_REQUEST_HISTORY_EQUIPMENT", columnList = "EQUIPMENT_ID"),
        @Index(name = "IDX_REPAIR_REQUEST_HISTORY_FAULT_TYPE", columnList = "FAULT_TYPE_ID"),
        @Index(name = "IDX_REPAIR_REQUEST_HISTORY_LOCATION", columnList = "LOCATION_ID")


})  // Исправьте опечатку в названии таблицы (HISTORY вместо HISTORY)
@Entity
public class RepairRequestHistory {

    @JmixGeneratedValue
    @Id
    @Column(name = "ID", nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;
    @InstanceName
    @Column(name = "DESCRIPTION", length = 1000)
    private String description;

    @Temporal(TemporalType.DATE)
    @Column(name = "COMPLETION_DATE")
    private Date completionDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOCATION_ID")
    private Location location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROOM_ID")
    private Room room;

    @Column(name = "EQUIPMENT_TYPE")
    private String equipmentType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EQUIPMENT_ID")
    private Equipment equipment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FAULT_TYPE_ID")
    private FaultType faultType;

    public void setEquipmentType(EquipmentType equipmentType) {
        this.equipmentType = equipmentType == null ? null : equipmentType.getId();
    }

    public EquipmentType getEquipmentType() {
        return equipmentType == null ? null : EquipmentType.fromId(equipmentType);
    }

    // Геттеры и сеттеры
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public FaultType getFaultType() {
        return faultType;
    }

    public void setFaultType(FaultType faultType) {
        this.faultType = faultType;
    }
}