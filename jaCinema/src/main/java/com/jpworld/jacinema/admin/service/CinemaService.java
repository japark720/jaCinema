package com.jpworld.jacinema.admin.service;

import com.jpworld.jacinema.admin.domain.Cinema;
import com.jpworld.jacinema.admin.domain.Region;
import com.jpworld.jacinema.admin.dto.CinemaRequest;
import com.jpworld.jacinema.admin.repository.CinemaRepository;
import com.jpworld.jacinema.admin.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CinemaService {

    private final CinemaRepository cinemaRepository;
    private final RegionRepository regionRepository;

    public List<Cinema> findAll() {
        return cinemaRepository.findAll();
    }

    public Cinema findByCinemaId(Long cinemaId) {
        return cinemaRepository.findById(cinemaId).orElse(null);
    }

    public boolean deleteCinema(Long cinemaId) {
        if(cinemaRepository.existsById(cinemaId)) {
            cinemaRepository.deleteById(cinemaId);
            return true;
        }
        return false;
    }

    public Cinema save(CinemaRequest cinemaRequest) {
        Cinema cinema = Cinema.builder()
                .cinemaRequest(cinemaRequest)
                .build();

        return cinemaRepository.save(cinema);
    }
}
