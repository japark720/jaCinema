package com.jpworld.jacinema.admin.dto;

import com.jpworld.jacinema.admin.AdminResultMessage;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TheaterTimeRequest {

    @NotNull(message = AdminResultMessage.THEATER_TIME_THEATER_ID)
    private Long theaterId;

    private String time;
}
