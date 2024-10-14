package com.jpworld.jacinema.admin.controller;

import com.jpworld.jacinema.admin.dto.MovieRequest;
import com.jpworld.jacinema.admin.dto.MovieResponse;
import com.jpworld.jacinema.admin.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/movie")
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/list")
    public ResponseEntity<?> list() {
        MovieResponse movieResponse = movieService.findAll();
        return new ResponseEntity<>(movieResponse, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addMovie(@Valid @RequestBody MovieRequest movieRequest, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            MovieResponse errorResponse = movieService.validationError(bindingResult);
            return new ResponseEntity<>(errorResponse, HttpStatus.OK);
        }

        MovieResponse successResponse = movieService.addMovie(movieRequest);
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<?> findMovie(@RequestParam("id") Long movieId) {
        MovieResponse findMovie = movieService.findMovieId(movieId);
        return new ResponseEntity<>(findMovie, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateMovie(@RequestBody MovieRequest movieRequest) {
        MovieResponse findMovie = movieService.updateMovie(movieRequest);
        return new ResponseEntity<>(findMovie, HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteMovie(@RequestBody MovieRequest movieRequest) {
        MovieResponse result = movieService.deleteMovie(movieRequest.getMovieId());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
