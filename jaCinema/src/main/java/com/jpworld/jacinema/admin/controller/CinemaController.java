package com.jpworld.jacinema.admin.controller;

import com.jpworld.jacinema.admin.domain.Cinema;
import com.jpworld.jacinema.admin.dto.CinemaRequest;
import com.jpworld.jacinema.admin.dto.CinemaResponse;
import com.jpworld.jacinema.admin.service.CinemaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/cinema")
public class CinemaController {

    private final CinemaService cinemaService;

    @GetMapping("/list")
    public ResponseEntity<?> list() {
        CinemaResponse cinemas = cinemaService.findAll();
        return new ResponseEntity<>(cinemas, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCinema(@Valid @RequestBody CinemaRequest cinemaRequest, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            CinemaResponse errorResponse = cinemaService.validationError(bindingResult);
            return new ResponseEntity<>(errorResponse, HttpStatus.OK);
        }

        CinemaResponse addCinema = cinemaService.addCinema(cinemaRequest);
        return new ResponseEntity<>(addCinema, HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<?> findCinema(@RequestParam("id") Long cinemaId) {
        CinemaResponse findCinema = cinemaService.findCinemaId(cinemaId);
        return new ResponseEntity<>(findCinema, HttpStatus.OK);
    }

    @PostMapping("/modify")
    public ResponseEntity<?> updateCinema(@RequestBody CinemaRequest cinemaRequest) {
        CinemaResponse findCinema = cinemaService.updateCinema(cinemaRequest);
        return new ResponseEntity<>(findCinema, HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteCinema(@RequestBody CinemaRequest cinemaRequest) {
        CinemaResponse result = cinemaService.deleteCinema(cinemaRequest.getCinemaId());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
