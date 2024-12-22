package com.company.helpdesk.entity;

import io.jmix.core.metamodel.datatype.EnumClass;

import org.springframework.lang.Nullable;


public enum JobTitle implements EnumClass<String> {

    DEPUTAT("Deputat", 1),
    POMDEPUTAT("Pomdeputat", 2),
    SECRETAR("Secretar", 3),
    BUGHALTER("Bughalter", 4);

    private final String id;
    private final int priority;

    JobTitle(String id, int priority) {
        this.id = id;
        this.priority = priority;
    }

    public String getId() {
        return id;
    }

    public int getPriority() {
        return priority;
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
