package com.jpworld.jacinema.admin.dto;

import com.jpworld.jacinema.admin.AdminResultMessage;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TheaterUpdateRequest {

    @NotNull(message = AdminResultMessage.THEATER_ID_REQUIRED)
    private Long theaterId;
    private String name;
    private Long cinemaId;
    private Long movieId;

}
