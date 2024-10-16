package com.jpworld.jacinema.admin.domain;

import com.jpworld.jacinema.member.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@RequiredArgsConstructor
public class TheaterTime extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "theater_time_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theater_id")
    private Theater theater;
    private String time;

    @Builder
    public TheaterTime(String time, Theater theater) {
        this.time = time;
        this.theater = theater;
    }

    public TheaterTime updateTheaterTime(String time, Theater theater) {
        if(time != null) this.time = time;
        if(theater != null) this.theater = theater;
        return this;
    }
}
