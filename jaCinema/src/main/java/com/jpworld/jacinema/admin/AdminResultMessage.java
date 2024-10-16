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

    public static final String CINEMA_NAME_REQUIRED = "극장 이름을 입력해 주세요.";
    public static final String CINEMA_ADDRESS_REQUIRED = "극장 주소를 입력해 주세요.";
    public static final String CINEMA_REGION_REQUIRED = "극장 지역을 입력해 주세요.";

    public static final List<String> CINEMA_REQUIRED_LIST = Arrays.asList(
            CINEMA_NAME_REQUIRED,
            CINEMA_ADDRESS_REQUIRED,
            CINEMA_REGION_REQUIRED
    );

    public static final String THEATER_ID_REQUIRED = "상영관 아이디를 입력해 주세요.";
    public static final String THEATER_NAME_REQUIRED = "상영관 이름을 입력해 주세요.";
    public static final String THEATER_CINEMA_REQUIRED = "영화 아이디를 입력해 주세요.";
    public static final String THEATER_MOVIE_REQUIRED = "극장 아이디를 입력해 주세요.";

    public static final List<String> THEATER_REQUIRED_LIST = Arrays.asList(
            THEATER_NAME_REQUIRED,
            THEATER_CINEMA_REQUIRED,
            THEATER_MOVIE_REQUIRED
    );

    public static final String THEATER_TIME_TIME = "상영시간을 입력해 주세요.";
    public static final String THEATER_TIME_THEATER_ID = "상영관 아이디를 입력해 주세요.";

    public static final List<String> THEATER_TIME_REQUIRED_LIST = Arrays.asList(
            THEATER_TIME_TIME,
            THEATER_TIME_THEATER_ID
    );

    public static final String SUCCESS = "success";
    public static final String FAIL = "fail";
    public static final String NOT_FOUND = "notFound";

    public static final String NOT_FOUND_MOVIE_ID = "not found movieId";
    public static final String NOT_FOUND_CINEMA_ID = "not found cinemaId";

    public static final String NOT_FOUND_THEATER_ID= "not found theaterId";

 }
