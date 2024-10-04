package com.jpworld.jacinema.admin.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CinemaRequest {

    private Long cinemaId;

    private String cinemaName;
    private String address;
    private String detailAddress;
}
