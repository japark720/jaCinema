package com.jpworld.jacinema.admin.repository;

import com.jpworld.jacinema.admin.domain.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    Optional<Seat> findByTheaterTimeId(Long theaterTimeId);
}
