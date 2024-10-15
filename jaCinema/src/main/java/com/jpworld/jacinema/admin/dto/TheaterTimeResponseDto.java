package com.jpworld.jacinema.admin.dto;

import com.jpworld.jacinema.admin.domain.TheaterTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class TheaterTimeResponseDto {
    private Long theaterTimeId;
    private String time;

    public static TheaterTimeResponseDto fromEntity(TheaterTime theaterTime) {
        return TheaterTimeResponseDto.builder()
                .theaterTimeId(theaterTime.getId())
                .time(theaterTime.getTime())
                .build();
    }
}
