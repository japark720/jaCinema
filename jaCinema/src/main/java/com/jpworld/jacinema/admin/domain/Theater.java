package com.jpworld.jacinema.admin.domain;

import com.jpworld.jacinema.member.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@RequiredArgsConstructor
public class Theater extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "theater_id")
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cinema_id")
    private Cinema cinema;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @Builder
    public Theater (String name, Cinema cinema, Movie movie) {
        this.name = name;
        this.cinema = cinema;
        this.movie = movie;
    }

    public Theater updateTheater(String name, Cinema cinema, Movie movie) {
        if(name != null) this.name = name;
        if(cinema != null) this.cinema = cinema;
        if(movie != null) this.movie = movie;

        return this;
    }
}
