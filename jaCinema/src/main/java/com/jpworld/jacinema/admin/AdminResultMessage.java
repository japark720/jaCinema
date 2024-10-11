package com.jpworld.jacinema.admin;

import java.util.Arrays;
import java.util.List;

public class AdminResultMessage {
    public static final String MOVIE_TITLE_REQUIRED = "영화 제목을 입력해 주세요.";
    public static final String MOVIE_GENRE_REQUIRED = "영화 장르를 입력해 주세요.";
    public static final String MOVIE_COUNTRY_REQUIRED = "영화 국가를 입력해 주세요.";
    public static final String MOVIE_AGE_LIMIT = "상영관람가를 입력해 주세요.";
    public static final String MOVIE_DIRECTOR = "감독 이름을 입력해 주세요.";
    public static final String MOVIE_ACTORS = "배우 이름을 입력해 주세요.";
    public static final String MOVIE_WRITER = "작성자 이름 입력";

    public static final List<String> MOVIE_REQUIRED_LIST = Arrays.asList(
            MOVIE_TITLE_REQUIRED,
            MOVIE_GENRE_REQUIRED,
            MOVIE_COUNTRY_REQUIRED,
            MOVIE_AGE_LIMIT,
            MOVIE_DIRECTOR,
            MOVIE_ACTORS,
            MOVIE_WRITER
    );


    public static final String SUCCESS = "success";
    public static final String FAIL = "fail";
    public static final String NOT_FOUND = "notFound";
}
