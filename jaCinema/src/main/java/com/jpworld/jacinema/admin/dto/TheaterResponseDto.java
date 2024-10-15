package com.jpworld.jacinema.admin.dto;

import com.jpworld.jacinema.admin.domain.Theater;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class TheaterResponseDto {
    private Long theaterId;
    private String name;
    private CinemaResponseDto cinema;
    private MovieResponseDto movie;

    public static TheaterResponseDto fromEntity(Theater theater) {
        return TheaterResponseDto.builder()
                .theaterId(theater.getId())
                .name(theater.getName())
                .cinema(CinemaResponseDto.fromEntity(theater.getCinema()))
                .movie(MovieResponseDto.fromEntity(theater.getMovie()))
                .build();
    }
}
