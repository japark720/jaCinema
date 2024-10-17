package com.jpworld.jacinema.admin.dto;

import com.jpworld.jacinema.admin.AdminResultMessage;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SeatRequest {

    @NotNull(message = AdminResultMessage.SEAT_THEATER_TIME)
    private Long theaterTimeId;

    @NotNull(message = AdminResultMessage.TOTAL_SEATS)
    private int totalSeats;
}
