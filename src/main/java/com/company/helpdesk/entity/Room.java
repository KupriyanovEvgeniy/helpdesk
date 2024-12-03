package com.company.helpdesk.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;

import java.util.UUID;

@JmixEntity
@Table(name = "ROOM", indexes = {
        @Index(name = "IDX_ROOM_LOCATION", columnList = "LOCATION_ID")
})
@Entity
public class Room {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;
    @Column(name = "ROOM_NAME")
    private String roomName;
    @JoinColumn(name = "LOCATION_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Location location;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}