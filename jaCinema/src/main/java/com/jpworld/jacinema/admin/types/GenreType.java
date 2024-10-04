package com.jpworld.jacinema.admin.types;

import lombok.Getter;

/**
 * 장르 타입
 * DRAMA        : 드라마
 * FANTASY      : 판타지
 * FEAR         : 공포
 * ROMANCE      : 로맨스
 * THRILLER     : 스릴러
 * COMEDY       : 코미디
 * ANIMATION    : 애니메이션
 * SF           : SF
 * ACTION       : 액션
 */
public enum GenreType {
    DRAMA("드라마"),
    FANTASY("판타지"),
    FEAR("공포"),
    ROMANCE("로맨스"),
    THRILLER("스릴러"),
    COMEDY("코메디"),
    ANIMATION("애니메이션"),
    SF("SF"),
    ACTION("액션");

    @Getter
    final String desc;

    GenreType(String desc) {
        this.desc = desc;
    }
}
