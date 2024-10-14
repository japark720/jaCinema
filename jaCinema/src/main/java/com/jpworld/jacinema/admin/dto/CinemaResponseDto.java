package com.jpworld.jacinema.admin.dto;

import com.jpworld.jacinema.admin.domain.Cinema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class CinemaResponseDto {
    private Long cinemaId;
    private Long regionId;

    private String cinemaName;
    private String address;
    private String detailAddress;

    public static CinemaResponseDto fromEntity(Cinema cinema) {
        return CinemaResponseDto.builder()
                .cinemaId(cinema.getId())
                .cinemaName(cinema.getCinemaName())
                .address(cinema.getAddress())
                .detailAddress(cinema.getDetailAddress())
                .regionId(cinema.getRegion().getId())
                .build();
    }
}
