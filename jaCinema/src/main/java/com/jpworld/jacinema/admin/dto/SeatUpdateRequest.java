package com.jpworld.jacinema.admin.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SeatUpdateRequest {
    private Long SeatId;
    private Integer totalSeats;
    private Integer reservedSeats;
}
