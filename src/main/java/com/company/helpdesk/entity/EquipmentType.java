package com.company.helpdesk.entity;

import io.jmix.core.metamodel.datatype.EnumClass;

import org.springframework.lang.Nullable;


public enum EquipmentType implements EnumClass<String> {

    PRINTER("Printer"),
    COMPUTER("Computer"),
    MONITOR("Monitor"),
    SCANNER("Scanner"),
    OTHER("Other");

    private final String id;

    EquipmentType(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static EquipmentType fromId(String id) {
        for (EquipmentType at : EquipmentType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}