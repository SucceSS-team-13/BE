package com.example.SucceSS.domain.enums;

public enum PersonalityJudgement {
    EMOTIONAL("감정"),
    LOGICAL("이성");

    private final String description;

    PersonalityJudgement(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
