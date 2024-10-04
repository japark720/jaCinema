package com.jpworld.jacinema.admin.controller;

import com.jpworld.jacinema.admin.domain.Movie;
import com.jpworld.jacinema.admin.dto.MovieRequest;
import com.jpworld.jacinema.admin.dto.MovieResponse;
import com.jpworld.jacinema.admin.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/movie")
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/list")
    public ResponseEntity<?> list() {
        List<Movie> movies = movieService.findAllMovies();

        Map<String, Object> response = new HashMap<>();
        response.put("movies", movies);
        return new ResponseEntity<>(response, HttpStatus.OK);
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

    @PostMapping("/modify")
    public ResponseEntity<?> modifyMovie(@RequestBody MovieRequest movieRequest) {
        Movie findMovie = movieService.findByMovieId(movieRequest.getMovieId());
        findMovie.updateMovie(movieRequest);
        movieService.updateMovie(findMovie);
        return new ResponseEntity<>(findMovie, HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteMovie(@RequestBody MovieRequest movieRequest) {
        boolean result = movieService.deleteMovie(movieRequest.getMovieId());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
