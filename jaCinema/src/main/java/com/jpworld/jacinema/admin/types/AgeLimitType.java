package com.jpworld.jacinema.admin.types;

import lombok.Getter;

/**
 * 연령 제한
 * ALL       : 전체 관람
 * TWELVE    : 12세 이상
 * FIFTEEN   : 15세 이상
 * ADULT     : 청소년 관람불가
 */
public enum AgeLimitType {
    ALL("전체관람가"),
    TWELVE("12세 이상"),
    FIFTEEN("15세 이상"),
    ADULT("청소년 관람불가");

    @Getter
    private final String desc;

    AgeLimitType(String desc) {
        this.desc = desc;
    }
}
