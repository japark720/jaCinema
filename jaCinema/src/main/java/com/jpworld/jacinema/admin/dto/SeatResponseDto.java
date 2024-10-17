package com.jpworld.jacinema.admin.dto;

import com.jpworld.jacinema.admin.domain.Seat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class SeatResponseDto {
    private Long seatId;
    private int totalSeats;
    private int reservedSeats;
    private Long theaterTimeId;

    public static SeatResponseDto fromEntity(Seat seat) {
        return SeatResponseDto.builder()
                .seatId(seat.getId())
                .totalSeats(seat.getTotalSeats())
                .reservedSeats(seat.getReservedSeats())
                .theaterTimeId(seat.getTheaterTime().getId())
                .build();
    }
}
