package com.jpworld.jacinema.admin.service;

import com.jpworld.jacinema.admin.AdminResultCode;
import com.jpworld.jacinema.admin.AdminResultMessage;
import com.jpworld.jacinema.admin.domain.Theater;
import com.jpworld.jacinema.admin.domain.TheaterTime;
import com.jpworld.jacinema.admin.dto.*;
import com.jpworld.jacinema.admin.repository.TheaterRepository;
import com.jpworld.jacinema.admin.repository.TheaterTimeRepository;
import com.jpworld.jacinema.exceptions.EntityNotFountException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TheaterTimeService {

    private final TheaterTimeRepository theaterTimeRepository;
    private final TheaterRepository theaterRepository;

    public TheaterTimeResponse findByTheaterId(Long theaterId) {
        List<TheaterTime> theaterTimes = theaterTimeRepository.findByTheaterId(theaterId);
        List<TheaterTimeResponseDto> list = theaterTimes.stream().map(TheaterTimeResponseDto::fromEntity).toList();

        return TheaterTimeResponse.builder()
                .theaterTimes(list)
                .message(AdminResultMessage.SUCCESS)
                .resultCode(AdminResultCode.SUCCESS_CODE)
                .build();
    }

    public TheaterTimeResponse addTheaterTime(TheaterTimeRequest theaterTimeRequest) {
        Theater findTheater = theaterRepository.findById(theaterTimeRequest.getTheaterId()).orElseThrow(() ->
                new EntityNotFountException(AdminResultMessage.NOT_FOUND_THEATER_ID, AdminResultCode.NOT_FOUND_ID_CODE));

        if(theaterTimeRequest.getTime() == null) {
            throw new EntityNotFountException(AdminResultMessage.THEATER_TIME_TIME, AdminResultCode.PARAMETER_IS_NULL);
        }

        TheaterTime theaterTime = TheaterTime.builder()
                .time(theaterTimeRequest.getTime())
                .theater(findTheater)
                .build();

        theaterTimeRepository.save(theaterTime);

        return TheaterTimeResponse.builder()
                .message(AdminResultMessage.SUCCESS)
                .resultCode(AdminResultCode.SUCCESS_CODE)
                .build();
    }
    public TheaterTimeResponse updateTheaterTime(TheaterTimeUpdateRequest theaterTimeUpdateRequest) {
        Long theaterTimeId = theaterTimeUpdateRequest.getTheaterTimeId();
        if (theaterTimeId == null) {
            throw new EntityNotFountException(AdminResultMessage.NOT_FOUND_THEATER_TIME_ID,
                    AdminResultCode.NOT_FOUND_ID_CODE);
        }

        TheaterTime theaterTime = theaterTimeRepository.findById(theaterTimeId)
                .orElseThrow(() -> new EntityNotFountException(AdminResultMessage.NOT_FOUND_THEATER_TIME_ID,
                        AdminResultCode.NOT_FOUND_ID_CODE));

        String time = theaterTimeUpdateRequest.getTime();

        Theater theater = Optional.ofNullable(theaterTimeUpdateRequest.getTheaterId())
                .map(id -> theaterRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFountException(AdminResultMessage.NOT_FOUND_THEATER_ID,
                                AdminResultCode.NOT_FOUND_ID_CODE)))
                .orElse(null);

        theaterTime.updateTheaterTime(time, theater);
        theaterTimeRepository.save(theaterTime);

        return TheaterTimeResponse.builder()
                .message(AdminResultMessage.SUCCESS)
                .resultCode(AdminResultCode.SUCCESS_CODE)
                .build();
    }

    public TheaterTimeResponse deleteTheaterTime(TheaterTimeDeleteRequest theaterTimeDeleteRequest) {
        Long theaterTimeId = theaterTimeDeleteRequest.getTheaterTimeId();

        if(theaterTimeId == null) {
            throw new EntityNotFountException(AdminResultMessage.NOT_FOUND_THEATER_TIME_ID, AdminResultCode.NOT_FOUND_ID_CODE);
        }

        if(theaterTimeRepository.existsById(theaterTimeId)) {
            theaterTimeRepository.deleteById(theaterTimeId);
            return TheaterTimeResponse.builder()
                    .message(AdminResultMessage.SUCCESS)
                    .resultCode(AdminResultCode.SUCCESS_CODE)
                    .build();
        } else {
            throw new EntityNotFountException(AdminResultMessage.NOT_FOUND_THEATER_TIME_ID, AdminResultCode.NOT_FOUND_ID_CODE);
        }
    }

    public TheaterTimeResponse validationError(BindingResult bindingResult) {
        String errMsg = bindingResult.getFieldError().getDefaultMessage();
        String code = AdminResultMessage.THEATER_TIME_REQUIRED_LIST
                .contains(errMsg) ? AdminResultCode.PARAMETER_IS_NULL : AdminResultCode.ERROR_CODE;

        return TheaterTimeResponse.builder()
                .message(errMsg)
                .resultCode(code)
                .build();
    }
}
