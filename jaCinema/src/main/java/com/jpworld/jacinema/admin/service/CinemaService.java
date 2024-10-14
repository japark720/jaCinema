package com.jpworld.jacinema.admin.service;

import com.jpworld.jacinema.admin.AdminResultCode;
import com.jpworld.jacinema.admin.AdminResultMessage;
import com.jpworld.jacinema.admin.domain.Cinema;
import com.jpworld.jacinema.admin.dto.*;
import com.jpworld.jacinema.admin.repository.CinemaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CinemaService {

    private final CinemaRepository cinemaRepository;

    public CinemaResponse findAll() {
        List<Cinema> findAllCinema = cinemaRepository.findAll();
        List<CinemaResponseDto> cinemas = findAllCinema.stream().map(cinema -> CinemaResponseDto.builder()
                .cinemaId(cinema.getId())
                .regionId(cinema.getRegion().getId())
                .cinemaName(cinema.getCinemaName())
                .address(cinema.getAddress())
                .detailAddress(cinema.getDetailAddress())
                .build()).collect(Collectors.toList());

        return CinemaResponse.builder()
                .cinemas(cinemas)
                .message(AdminResultMessage.SUCCESS)
                .resultCode(AdminResultCode.SUCCESS_CODE)
                .build();
    }

    public CinemaResponse addCinema(CinemaRequest cinemaRequest) {
        Cinema cinema = Cinema.builder()
                .cinemaRequest(cinemaRequest)
                .build();

        cinemaRepository.save(cinema);

        return CinemaResponse.builder()
                .message(AdminResultMessage.SUCCESS)
                .resultCode(AdminResultCode.SUCCESS_CODE)
                .build();
    }

    public CinemaResponse findCinemaId(Long id) {
        Optional<Cinema> findCinema = cinemaRepository.findById(id);

        if (findCinema.isPresent()) {
            Optional<CinemaResponseDto> cinemaResponseDto = findCinema.map(CinemaResponseDto::fromEntity);

            return CinemaResponse.builder()
                    .cinema(cinemaResponseDto.get())
                    .message(AdminResultMessage.SUCCESS)
                    .resultCode(AdminResultCode.SUCCESS_CODE)
                    .build();
        }
        return CinemaResponse.builder()
                .message(AdminResultMessage.NOT_FOUND)
                .resultCode(AdminResultCode.NOT_FOUND_CODE)
                .build();
    }

    public CinemaResponse updateCinema(CinemaRequest cinemaRequest) {
        Optional<Cinema> findCinema = cinemaRepository.findById(cinemaRequest.getCinemaId());

        if (findCinema.isEmpty()) {
            return CinemaResponse.builder()
                    .message(AdminResultMessage.NOT_FOUND)
                    .resultCode(AdminResultCode.NOT_FOUND_CODE)
                    .build();
        }

        Cinema updateCinema = findCinema.get().updateCinema(cinemaRequest);
        cinemaRepository.save(updateCinema);

        return CinemaResponse.builder()
                .message(AdminResultMessage.SUCCESS)
                .resultCode(AdminResultCode.SUCCESS_CODE)
                .build();
    }

    public CinemaResponse deleteCinema(Long cinemaId) {
        if (cinemaRepository.existsById(cinemaId)) {
            cinemaRepository.deleteById(cinemaId);
            return CinemaResponse.builder()
                    .message(AdminResultMessage.SUCCESS)
                    .resultCode(AdminResultCode.SUCCESS_CODE)
                    .build();
        }
        return CinemaResponse.builder()
                .message(AdminResultMessage.FAIL)
                .resultCode(AdminResultCode.ERROR_CODE)
                .build();
    }

    public CinemaResponse validationError(BindingResult bindingResult) {
        String errMsg = bindingResult.getFieldError().getDefaultMessage();
        String code = AdminResultMessage.CINEMA_REQUIRED_LIST
                .contains(errMsg) ? AdminResultCode.PARAMETER_IS_NULL : AdminResultCode.ERROR_CODE;

        return CinemaResponse.builder()
                .message(errMsg)
                .resultCode(code)
                .build();
    }
}
