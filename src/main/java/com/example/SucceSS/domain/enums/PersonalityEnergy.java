package com.example.SucceSS.domain.enums;

public enum PersonalityEnergy {
    INTROVERT("내향"),
    EXTROVERT("외향");

    private final String description;

    PersonalityEnergy(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
