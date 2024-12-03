package com.company.helpdesk.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;

import java.util.UUID;

@JmixEntity
@Table(name = "EQUIPMENT", indexes = {
        @Index(name = "IDX_EQUIPMENT_ROOM", columnList = "ROOM_ID")
})
@Entity
public class Equipment {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;
    @InstanceName
    @Column(name = "NAME")
    private String name;
    @Column(name = "MODEL")
    private String model;
    @Column(name = "SERIAL_NUMBER")
    private String serialNumber;
    @JoinColumn(name = "ROOM_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Room room;
    @Column(name = "EQUIPMENT_TYPE")
    private String equipmentType;

    public EquipmentType getEquipmentType() {
        return equipmentType == null ? null : EquipmentType.fromId(equipmentType);
    }

    public void setEquipmentType(EquipmentType equipmentType) {
        this.equipmentType = equipmentType == null ? null : equipmentType.getId();
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}