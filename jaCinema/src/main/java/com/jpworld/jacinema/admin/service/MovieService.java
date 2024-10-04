package com.jpworld.jacinema.admin.service;

import com.jpworld.jacinema.admin.AdminResultMessage;
import com.jpworld.jacinema.admin.AdminResultCode;
import com.jpworld.jacinema.admin.domain.Movie;
import com.jpworld.jacinema.admin.dto.MovieRequest;
import com.jpworld.jacinema.admin.dto.MovieResponse;
import com.jpworld.jacinema.admin.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public List<Movie> findAllMovies() {
        return movieRepository.findAll();
    }

    public Movie findByMovieId(Long movieId) {
        return movieRepository.findById(movieId).orElse(null);
    }

    public MovieResponse addMovie(MovieRequest movieRequest) {
        Movie movie = Movie.builder()
                .movieRequest(movieRequest)
                .build();
        Movie saveMovie = movieRepository.save(movie);
        return MovieResponse.builder()
                .movie(saveMovie)
                .message(AdminResultMessage.SUCCESS)
                .resultCode(AdminResultCode.SUCCESS_CODE)
                .build();
    }

    public Movie updateMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public boolean deleteMovie(Long movieId) {
        if (movieRepository.existsById(movieId)) {
            movieRepository.deleteById(movieId);
            return true;
        } else {
            return false;
        }
    }

    public MovieResponse validationError(BindingResult bindingResult) {
        String errMsg = bindingResult.getFieldError().getDefaultMessage();
        String code = AdminResultMessage.MOVIE_REQUIRED_LIST
                .contains(errMsg) ? AdminResultCode.PARAMETER_IS_NULL : AdminResultCode.ERROR_CODE;

        return MovieResponse.builder()
                .message(errMsg)
                .resultCode(code)
                .build();
    }
}
