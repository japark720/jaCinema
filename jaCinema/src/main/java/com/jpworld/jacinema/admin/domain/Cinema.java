package com.jpworld.jacinema.admin.domain;

import com.jpworld.jacinema.admin.dto.CinemaRequest;
import com.jpworld.jacinema.member.domain.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@RequiredArgsConstructor
public class Cinema extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "cinema_id")
    private Long id;

    private String cinemaName;
    private String address;
    private String detailAddress;

    @Builder
    public Cinema(CinemaRequest cinemaRequest) {
        this.cinemaName = cinemaRequest.getCinemaName();
        this.address = cinemaRequest.getAddress();
        this.detailAddress = cinemaRequest.getDetailAddress();
    }

    public Cinema updateCinema(CinemaRequest cinemaRequest) {
        this.cinemaName = cinemaRequest.getCinemaName();
        this.address = cinemaRequest.getAddress();
        this.detailAddress = cinemaRequest.getDetailAddress();
        return this;
    }
}
