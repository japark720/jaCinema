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

    private String writer;

    @Builder
    public Movie(MovieRequest movieRequest) {
        this.id = movieRequest.getMovieId();
        this.title = movieRequest.getTitle();
        this.genre = movieRequest.getGenre();
        this.country = movieRequest.getCountry();
        this.ageLimit = movieRequest.getAgeLimit();
        this.director = movieRequest.getDirector();
        this.actors = movieRequest.getActors();
        this.writer = movieRequest.getWriter();
    }

    public Movie updateMovie(MovieRequest movieRequest) {
        if (movieRequest.getTitle() != null) {
            this.title = movieRequest.getTitle();
        }
        if (movieRequest.getGenre() != null) {
            this.genre = movieRequest.getGenre();
        }
        if (movieRequest.getCountry() != null) {
            this.country = movieRequest.getCountry();
        }
        if (movieRequest.getAgeLimit() != null) {
            this.ageLimit = movieRequest.getAgeLimit();
        }
        if (movieRequest.getDirector() != null) {
            this.director = movieRequest.getDirector();
        }
        if (movieRequest.getActors() != null) {
            this.actors = movieRequest.getActors();
        }
        return this;
    }
}
