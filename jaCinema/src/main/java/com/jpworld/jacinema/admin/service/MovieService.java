package com.jpworld.jacinema.admin.service;

import com.jpworld.jacinema.admin.AdminResultMessage;
import com.jpworld.jacinema.admin.AdminResultCode;
import com.jpworld.jacinema.admin.domain.Movie;
import com.jpworld.jacinema.admin.dto.MovieRequest;
import com.jpworld.jacinema.admin.dto.MovieResponse;
import com.jpworld.jacinema.admin.dto.MovieResponseDto;
import com.jpworld.jacinema.admin.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieResponse findAll() {
        List<Movie> movies = movieRepository.findAll();

        List<MovieResponseDto> list = movies.stream().map(movie -> MovieResponseDto.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .genre(movie.getGenre())
                .country(movie.getCountry())
                .ageLimit(movie.getAgeLimit())
                .director(movie.getDirector())
                .actors(movie.getActors())
                .writer(movie.getWriter())
                .build()
        ).collect(Collectors.toList());

        return MovieResponse.builder()
                .movies(list)
                .message(AdminResultMessage.SUCCESS)
                .resultCode(AdminResultCode.SUCCESS_CODE)
                .build();
    }

    public Optional<Movie> findByMovieId(Long movieId) {
        return movieRepository.findById(movieId);
    }

    public MovieResponse addMovie(MovieRequest movieRequest) {
        movieRepository.save(Movie.builder()
                .movieRequest(movieRequest)
                .build());

        return MovieResponse.builder()
                .message(AdminResultMessage.SUCCESS)
                .resultCode(AdminResultCode.SUCCESS_CODE)
                .build();
    }

    public MovieResponse updateMovie(MovieRequest movieRequest) {
        Optional<Movie> findMovie = findByMovieId(movieRequest.getMovieId());

        if (findMovie.isEmpty()) {
            return MovieResponse.builder()
                    .message(AdminResultMessage.NOT_FOUND)
                    .resultCode(AdminResultCode.NOT_FOUND_CODE)
                    .build();
        }

        Movie updateMovie = findMovie.get().updateMovie(movieRequest);
        movieRepository.save(updateMovie);

        return MovieResponse.builder()
                .message(AdminResultMessage.SUCCESS)
                .resultCode(AdminResultCode.SUCCESS_CODE)
                .build();
    }

    public MovieResponse deleteMovie(Long movieId) {
        if (movieRepository.existsById(movieId)) {
            movieRepository.deleteById(movieId);
            return MovieResponse.builder()
                    .message(AdminResultMessage.SUCCESS)
                    .resultCode(AdminResultCode.SUCCESS_CODE)
                    .build();
        } else {
            return MovieResponse.builder()
                    .message(AdminResultMessage.FAIL)
                    .resultCode(AdminResultCode.ERROR_CODE)
                    .build();
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
