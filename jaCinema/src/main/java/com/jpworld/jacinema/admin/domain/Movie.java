package com.jpworld.jacinema.admin.domain;

import com.jpworld.jacinema.admin.dto.MovieRequest;
import com.jpworld.jacinema.admin.types.AgeLimitType;
import com.jpworld.jacinema.admin.types.CountryType;
import com.jpworld.jacinema.admin.types.GenreType;
import com.jpworld.jacinema.member.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@RequiredArgsConstructor
public class Movie extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "movie_id")
    private Long id;

    private String title;

    @Enumerated(EnumType.STRING)
    private GenreType genre;

    @Enumerated(EnumType.STRING)
    private CountryType country;

    @Enumerated(EnumType.STRING)
    private AgeLimitType ageLimit;

    private String director;

    private String actors;

    private String createName;

    @Builder
    public Movie(MovieRequest movieRequest) {
        this.id = movieRequest.getMovieId();
        this.title = movieRequest.getTitle();
        this.genre = movieRequest.getGenre();
        this.country = movieRequest.getCountry();
        this.ageLimit = movieRequest.getAgeLimit();
        this.director = movieRequest.getDirector();
        this.actors = movieRequest.getActors();
        this.createName = movieRequest.getCreateName();
    }

    public Movie updateMovie(MovieRequest movieRequest) {
        this.title = movieRequest.getTitle();
        this.genre = movieRequest.getGenre();
        this.country = movieRequest.getCountry();
        this.ageLimit = movieRequest.getAgeLimit();
        this.director = movieRequest.getDirector();
        this.actors = movieRequest.getActors();

        return this;
    }
}
