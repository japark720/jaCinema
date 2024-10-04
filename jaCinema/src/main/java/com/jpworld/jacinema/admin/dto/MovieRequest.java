package com.jpworld.jacinema.admin.dto;

import com.jpworld.jacinema.admin.AdminResultMessage;
import com.jpworld.jacinema.admin.types.AgeLimitType;
import com.jpworld.jacinema.admin.types.CountryType;
import com.jpworld.jacinema.admin.types.GenreType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieRequest {

    private Long movieId;

    @NotNull(message = AdminResultMessage.MOVIE_TITLE_REQUIRED)
    private String title;

    @NotNull(message = AdminResultMessage.MOVIE_GENRE_REQUIRED)
    private GenreType genre;

    @NotNull(message = AdminResultMessage.MOVIE_COUNTRY_REQUIRED)
    private CountryType country;

    @NotNull(message = AdminResultMessage.MOVIE_AGE_LIMIT)
    private AgeLimitType ageLimit;

    @NotNull(message = AdminResultMessage.MOVIE_DIRECTOR)
    private String director;

    @NotNull(message = AdminResultMessage.MOVIE_ACTORS)
    private String actors;

    @NotNull(message = AdminResultMessage.MOVIE_CREATE_NAME)
    private String createName;
}
