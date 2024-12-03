package com.company.helpdesk.entity;

import io.jmix.core.metamodel.datatype.EnumClass;

import org.springframework.lang.Nullable;


public enum JobTitle implements EnumClass<String> {

    DEPUTAT("Deputat"),
    POMDEPUTAT("Pomdeputat"),
    SECRETAR("Secretar"),
    BUGHALTER("Bughalter");

    private final String id;

    JobTitle(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static JobTitle fromId(String id) {
        for (JobTitle at : JobTitle.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}