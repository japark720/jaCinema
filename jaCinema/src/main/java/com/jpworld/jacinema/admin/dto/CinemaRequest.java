package com.jpworld.jacinema.admin.dto;

import com.jpworld.jacinema.admin.AdminResultMessage;
import com.jpworld.jacinema.admin.domain.Region;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CinemaRequest {

    private Long cinemaId;

    @NotNull(message = AdminResultMessage.CINEMA_NAME_REQUIRED)
    private String cinemaName;

    @NotNull(message = AdminResultMessage.CINEMA_ADDRESS_REQUIRED)
    private String address;

    private String detailAddress;

    @NotNull(message = AdminResultMessage.CINEMA_REGION_REQUIRED)
    private Region region;
}
