package com.jpworld.jacinema.admin.dto;

import com.jpworld.jacinema.admin.AdminResultMessage;
import com.jpworld.jacinema.admin.domain.Theater;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TheaterTimeUpdateRequest {

    @NotNull(message = AdminResultMessage.THEATER_TIME_ID)
    private Long theaterTimeId;
    private String time;
    private Long theaterId;
}
