package com.jpworld.jacinema.admin.dto;

import com.jpworld.jacinema.admin.types.AgeLimitType;
import com.jpworld.jacinema.admin.types.CountryType;
import com.jpworld.jacinema.admin.types.GenreType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieRequest {

    private Long movieId;
    private String title;
    private GenreType genre;
    private CountryType country;
    private AgeLimitType ageLimit;
    private String director;
    private String actors;
    private String createName;
}
