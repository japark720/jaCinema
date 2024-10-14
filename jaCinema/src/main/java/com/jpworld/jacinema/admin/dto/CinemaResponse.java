package com.jpworld.jacinema.admin.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jpworld.jacinema.admin.domain.Cinema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class CinemaResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<CinemaResponseDto> cinemas;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private CinemaResponseDto cinema;

    private String message;
    private String resultCode;
}


