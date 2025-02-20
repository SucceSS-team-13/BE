package com.example.SucceSS.domain.enums;

public enum Hobby {
    SPORTS("운동"),
    TRAVEL("여행"),
    READING("독서"),
    CRAFT("공예"),
    GAMING("게임"),
    MOVIES("영화");

    private final String description;

    Hobby(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
