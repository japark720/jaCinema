package com.jpworld.jacinema.admin.domain;

import com.jpworld.jacinema.admin.dto.TheaterTimeRequest;
import com.jpworld.jacinema.member.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@RequiredArgsConstructor
public class TheaterTime extends BaseTimeEntity {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Theater theater;

    private String time;

    @Builder
    public TheaterTime(String time, Theater theater) {
        this.time = time;
        this.theater = theater;
    }
}
