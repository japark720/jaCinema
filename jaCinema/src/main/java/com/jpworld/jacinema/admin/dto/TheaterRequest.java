package com.jpworld.jacinema.admin.dto;

import com.jpworld.jacinema.admin.AdminResultMessage;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TheaterRequest {

    private Long theaterId;

    @NotNull(message = AdminResultMessage.THEATER_NAME_REQUIRED)
    private String name;

    @NotNull(message = AdminResultMessage.THEATER_CINEMA_REQUIRED)
    private Long cinemaId;

    @NotNull(message = AdminResultMessage.THEATER_MOVIE_REQUIRED)
    private Long movieId;
}
