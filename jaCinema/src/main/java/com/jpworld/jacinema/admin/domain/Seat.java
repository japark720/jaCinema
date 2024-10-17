package com.jpworld.jacinema.admin.domain;

import com.jpworld.jacinema.member.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@RequiredArgsConstructor
public class Seat extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "seat_id")
    private Long id;

    private int totalSeats;
    private int reservedSeats;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theater_time_id")
    private TheaterTime theaterTime;

    @Builder
    public Seat(int totalSeats, TheaterTime theaterTime) {
        this.totalSeats = totalSeats;
        this.theaterTime = theaterTime;
    }

    public Seat updateSeat(int totalSeats, int reservedSeats, TheaterTime theaterTime) {
        this.totalSeats = totalSeats;
        this.reservedSeats = reservedSeats;
        this.theaterTime = theaterTime;
        return this;
    }
}
