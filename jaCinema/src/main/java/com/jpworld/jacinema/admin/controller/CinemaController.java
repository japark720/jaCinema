package com.jpworld.jacinema.admin.controller;

import com.jpworld.jacinema.admin.domain.Cinema;
import com.jpworld.jacinema.admin.dto.CinemaRequest;
import com.jpworld.jacinema.admin.service.CinemaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/cinema")
public class CinemaController {

    private final CinemaService cinemaService;

    @GetMapping("/list")
    public ResponseEntity<?> list() {
        List<Cinema> cinemas = cinemaService.findAll();
        return new ResponseEntity<>(cinemas, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody CinemaRequest cinemaRequest) {
        Cinema cinema = Cinema.builder()
                .cinemaRequest(cinemaRequest)
                .build();

        Cinema saveCinema = cinemaService.save(cinema);
        return new ResponseEntity<>(saveCinema, HttpStatus.OK);
    }

    @PostMapping("/modify")
    public ResponseEntity<?> modify(@RequestBody CinemaRequest cinemaRequest) {
        Cinema findCinema = cinemaService.findByCinemaId(cinemaRequest.getCinemaId());
        findCinema.updateCinema(cinemaRequest);
        return new ResponseEntity<>(findCinema, HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody CinemaRequest cinemaRequest) {
        boolean result = cinemaService.deleteCinema(cinemaRequest.getCinemaId());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
