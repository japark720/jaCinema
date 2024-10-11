package com.jpworld.jacinema.admin.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class MovieResponse {
    private String message;
    private String resultCode;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<MovieResponseDto> movies;
}
