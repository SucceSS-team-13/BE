package com.example.SucceSS.domain.enums;

public enum DetailedHobby {
    // 운동 세부 취미
    GYM("헬스", HobbyCategory.SPORTS),
    YOGA("요가", HobbyCategory.SPORTS),
    TENNIS("테니스", HobbyCategory.SPORTS),
    PILATES("필라테스", HobbyCategory.SPORTS),
    SWIMMING("수영", HobbyCategory.SPORTS),
    GOLF("골프", HobbyCategory.SPORTS),
    CLIMBING("클라이밍", HobbyCategory.SPORTS),
    SOCCER("축구", HobbyCategory.SPORTS),
    BASKETBALL("농구", HobbyCategory.SPORTS),
    BOWLING("볼링", HobbyCategory.SPORTS),
    BADMINTON("배드민턴", HobbyCategory.SPORTS),
    RUNNING("러닝", HobbyCategory.SPORTS),

    // 여행 세부 취미
    DOMESTIC_TRAVEL("국내 여행", HobbyCategory.TRAVEL),
    OVERSEAS_TRAVEL("해외 여행", HobbyCategory.TRAVEL),
    BACKPACKING("백패킹", HobbyCategory.TRAVEL),
    CAMPING("캠핑", HobbyCategory.TRAVEL),
    CITY_TRAVEL("도시 여행", HobbyCategory.TRAVEL),
    FOOD_TRAVEL("맛집 탐방", HobbyCategory.TRAVEL),
    CULTURE_TRAVEL("문화 탐방", HobbyCategory.TRAVEL),
    HEALING_TRAVEL("힐링 여행", HobbyCategory.TRAVEL),

    // 독서 세부 취미
    NOVEL("소설", HobbyCategory.READING),
    poem("시집", HobbyCategory.READING),
    ESSAY("에세이 도서", HobbyCategory.READING),
    SELF_DEVELOPMENT("자기계발 도서", HobbyCategory.READING),
    HUMANITY("인문 도서", HobbyCategory.READING),
    HISTORY("역사 도서", HobbyCategory.READING),
    ECONOMY("경제/경영 도서", HobbyCategory.READING),
    PHILOSOPHY("철학 도서", HobbyCategory.READING),
    ART("예술 도서", HobbyCategory.READING),

    // 영화 세부 취미
    ROMANCE("로맨스 영화", HobbyCategory.MOVIES),
    COMEDY("코미디 영화", HobbyCategory.MOVIES),
    ACTION("액션 영화", HobbyCategory.MOVIES),
    THRILLER("스릴러 영화", HobbyCategory.MOVIES),
    HORROR("공포 영화", HobbyCategory.MOVIES),
    SF("SF 영화", HobbyCategory.MOVIES),
    FANTASY("판타지 영화", HobbyCategory.MOVIES),
    DRAMA("드라마", HobbyCategory.MOVIES),
    ANIMATION("애니메이션 영화", HobbyCategory.MOVIES),
    DOCUMENTARY("다큐멘터리 영화", HobbyCategory.MOVIES),

    // 게임 세부 취미
    RPG("RPG 게임", HobbyCategory.GAMES),
    FPS("FPS 게임", HobbyCategory.GAMES),
    ACTION_GAME("액션 게임", HobbyCategory.GAMES),
    STRATEGY("전략 게임", HobbyCategory.GAMES),
    SIMULATION("시뮬레이션 게임", HobbyCategory.GAMES),
    SPROTS_GAME("스포츠 게임", HobbyCategory.GAMES),
    PUZZLE("퍼즐 게임", HobbyCategory.GAMES),
    RHYTHM("음악/리듬 게임", HobbyCategory.GAMES),
    CARD("카드 게임", HobbyCategory.GAMES),
    MMORPG("MMORPG 게임", HobbyCategory.GAMES),

    // 공예 세부 취미
    KNITTING("뜨개질", HobbyCategory.CRAFT),
    EMBROIDERY("자수 놓기", HobbyCategory.CRAFT),
    XERAMIC("도자기", HobbyCategory.CRAFT),
    LEATHER_CRAFT("가죽공예", HobbyCategory.CRAFT),
    WOOD_CRAFT("목공예", HobbyCategory.CRAFT),
    BEADS_CRAFT("비즈공예", HobbyCategory.CRAFT),
    CANDLE("캔들/디퓨저", HobbyCategory.CRAFT),
    PAPER_CRAFT("페이퍼크래프트", HobbyCategory.CRAFT),
    MACRAME("마크라메", HobbyCategory.CRAFT),
    RESIN_ART("레진아트", HobbyCategory.CRAFT);

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
