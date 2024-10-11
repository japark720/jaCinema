package com.jpworld.jacinema.admin.domain;

import com.jpworld.jacinema.admin.dto.CinemaRequest;
import com.jpworld.jacinema.member.domain.BaseTimeEntity;
import jakarta.persistence.*;
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    @Builder
    public Cinema(CinemaRequest cinemaRequest) {
        this.cinemaName = cinemaRequest.getCinemaName();
        this.address = cinemaRequest.getAddress();
        this.detailAddress = cinemaRequest.getDetailAddress();
        this.region = cinemaRequest.getRegion();
    }

    public Cinema updateCinema(CinemaRequest cinemaRequest) {
        this.cinemaName = cinemaRequest.getCinemaName();
        this.address = cinemaRequest.getAddress();
        this.detailAddress = cinemaRequest.getDetailAddress();
        return this;
    }
}
