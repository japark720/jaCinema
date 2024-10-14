package com.jpworld.jacinema.admin.dto;

import com.jpworld.jacinema.admin.domain.Movie;
import com.jpworld.jacinema.admin.types.AgeLimitType;
import com.jpworld.jacinema.admin.types.CountryType;
import com.jpworld.jacinema.admin.types.GenreType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MovieResponseDto {

    private Long id;
    private String title;
    private GenreType genre;
    private CountryType country;
    private AgeLimitType ageLimit;
    private String director;
    private String actors;
    private String writer;

    public static MovieResponseDto fromEntity(Movie movie) {
        return MovieResponseDto.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .genre(movie.getGenre())
                .country(movie.getCountry())
                .ageLimit(movie.getAgeLimit())
                .director(movie.getDirector())
                .actors(movie.getActors())
                .writer(movie.getWriter())
                .build();
    }
}
