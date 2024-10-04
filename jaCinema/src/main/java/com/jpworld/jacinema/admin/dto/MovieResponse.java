package com.jpworld.jacinema.admin.dto;

import com.jpworld.jacinema.admin.domain.Movie;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Setter
@Getter
@Builder
public class MovieResponse {
    private String message;
    private Movie movie;
    private String resultCode;
}
