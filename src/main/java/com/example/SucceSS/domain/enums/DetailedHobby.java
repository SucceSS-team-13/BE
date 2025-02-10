package com.example.SucceSS.domain.enums;

public enum DetailedHobby {
    // 운동 세부 취미
    GYM("헬스", HobbyCategory.SPORTS),
    YOGA("요가", HobbyCategory.SPORTS),
    TENNIS("테니스", HobbyCategory.SPORTS),

    // 여행 세부 취미
    DOMESTIC_TRAVEL("국내 여행", HobbyCategory.TRAVEL),
    CAMPING("캠핑", HobbyCategory.TRAVEL),

    // 독서 세부 취미
    FICTION("소설 읽기", HobbyCategory.READING),
    ESSAY("에세이 읽기", HobbyCategory.READING),

    // 영화 세부 취미
    ACTION_MOVIES("액션 영화", HobbyCategory.MOVIES),
    ROMANCE_MOVIES("로맨스 영화", HobbyCategory.MOVIES),

    // 게임 세부 취미
    RPG("RPG 게임", HobbyCategory.GAMES),
    FPS("FPS 게임", HobbyCategory.GAMES),

    // 공예 세부 취미
    KNITTING("뜨개질", HobbyCategory.CRAFT),
    LEATHER_CRAFT("가죽공예", HobbyCategory.CRAFT);

    private final String description;
    private final HobbyCategory category;

    DetailedHobby(String description, HobbyCategory category) {
        this.description = description;
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public HobbyCategory getCategory() {
        return category;
    }
}
