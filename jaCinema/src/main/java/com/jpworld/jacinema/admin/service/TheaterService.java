package com.jpworld.jacinema.admin.service;

import com.jpworld.jacinema.admin.AdminResultCode;
import com.jpworld.jacinema.admin.AdminResultMessage;
import com.jpworld.jacinema.admin.domain.Cinema;
import com.jpworld.jacinema.admin.domain.Movie;
import com.jpworld.jacinema.admin.domain.Theater;
import com.jpworld.jacinema.admin.dto.TheaterRequest;
import com.jpworld.jacinema.admin.dto.TheaterResponse;
import com.jpworld.jacinema.admin.dto.TheaterResponseDto;
import com.jpworld.jacinema.admin.dto.TheaterUpdateRequest;
import com.jpworld.jacinema.admin.repository.CinemaRepository;
import com.jpworld.jacinema.admin.repository.MovieRepository;
import com.jpworld.jacinema.admin.repository.TheaterRepository;
import com.jpworld.jacinema.exceptions.EntityNotFountException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TheaterService {

    private final TheaterRepository theaterRepository;
    private final CinemaRepository cinemaRepository;
    private final MovieRepository movieRepository;

    public TheaterResponse findAll() {
        List<Theater> findAllTheaters = theaterRepository.findAll();

        List<TheaterResponseDto> result = findAllTheaters.stream().map(TheaterResponseDto::fromEntity).toList();

        return TheaterResponse.builder()
                .theaters(result)
                .message(AdminResultMessage.SUCCESS)
                .resultCode(AdminResultCode.SUCCESS_CODE)
                .build();
    }

    public TheaterResponse addTheater(TheaterRequest theaterRequest) {
        // movieId, cinemaId가 존재 하는지 확인
        Optional<Movie> movieOptional = movieRepository.findById(theaterRequest.getMovieId());
        Optional<Cinema> cinemaOptional = cinemaRepository.findById(theaterRequest.getCinemaId());

        if (movieOptional.isEmpty()) {
            return TheaterResponse.builder()
                    .message(AdminResultMessage.NOT_FOUND_MOVIE_ID)
                    .resultCode(AdminResultCode.NOT_FOUND_ID_CODE)
                    .build();
        }

        if (cinemaOptional.isEmpty()) {
            return TheaterResponse.builder()
                    .message(AdminResultMessage.NOT_FOUND_CINEMA_ID)
                    .resultCode(AdminResultCode.NOT_FOUND_ID_CODE)
                    .build();
        }

        Theater theater = Theater.builder()
                .name(theaterRequest.getName())
                .cinema(cinemaOptional.get())
                .movie(movieOptional.get())
                .build();

        theaterRepository.save(theater);

        return TheaterResponse.builder()
                .message(AdminResultMessage.SUCCESS)
                .resultCode(AdminResultCode.SUCCESS_CODE)
                .build();
    }

    public TheaterResponse updateTheater(TheaterUpdateRequest theaterUpdateRequest) {
        Theater existingTheater = theaterRepository.findById(theaterUpdateRequest.getTheaterId())
                .orElseThrow(() -> new EntityNotFountException(
                        AdminResultMessage.NOT_FOUND_THEATER_ID,
                        AdminResultCode.NOT_FOUND_ID_CODE
                ));

        Cinema cinema = updateCinema(theaterUpdateRequest.getCinemaId());
        Movie movie = updateMovie(theaterUpdateRequest.getMovieId());

        existingTheater.updateTheater(theaterUpdateRequest.getName(), cinema, movie);
        theaterRepository.save(existingTheater);

        return TheaterResponse.builder()
                .message(AdminResultMessage.SUCCESS)
                .resultCode(AdminResultCode.SUCCESS_CODE)
                .build();
    }

    public TheaterResponse deleteTheater(Long theaterId) {
        if (theaterRepository.existsById(theaterId)) {
            theaterRepository.deleteById(theaterId);
            return TheaterResponse.builder()
                    .message(AdminResultMessage.SUCCESS)
                    .resultCode(AdminResultCode.SUCCESS_CODE)
                    .build();
        }
        return TheaterResponse.builder()
                .message(AdminResultMessage.FAIL)
                .resultCode(AdminResultCode.ERROR_CODE)
                .build();
    }

    public TheaterResponse validationError(BindingResult bindingResult) {
        String errMsg = bindingResult.getFieldError().getDefaultMessage();
        String code = AdminResultMessage.THEATER_REQUIRED_LIST
                .contains(errMsg) ? AdminResultCode.PARAMETER_IS_NULL : AdminResultCode.ERROR_CODE;

        return TheaterResponse.builder()
                .message(errMsg)
                .resultCode(code)
                .build();
    }

    private Cinema updateCinema(Long cinemaId) {
        if (cinemaId == null) return null;

        return cinemaRepository.findById(cinemaId)
                .orElseThrow(() -> new EntityNotFountException(
                        AdminResultMessage.NOT_FOUND_CINEMA_ID,
                        AdminResultCode.NOT_FOUND_ID_CODE
                ));
    }

    private Movie updateMovie(Long movieId) {
        if (movieId == null) return null;

        return movieRepository.findById(movieId)
                .orElseThrow(() -> new EntityNotFountException(
                        AdminResultMessage.NOT_FOUND_MOVIE_ID,
                        AdminResultCode.NOT_FOUND_ID_CODE
                ));
    }

}
