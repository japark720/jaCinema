package com.jpworld.jacinema.admin.types;

import lombok.Getter;

/**
 * 제작 국가
 * FR   : 프랑스
 * GB   : 영국
 * HK   : 홍콩
 * JP   : 일본
 * KR   : 한국
 * US   : 미국
 * ETC  : 기타
 */
public enum CountryType {
    FR("프랑스"), GB("영국"), HK("홍콩"), JP("일본"), KR("한국"), US("미국"), ETC("기타");

    @Getter
    private final String desc;

    CountryType(String desc) {
        this.desc = desc;
    }
}
