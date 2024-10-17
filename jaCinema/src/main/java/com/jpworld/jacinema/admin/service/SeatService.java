package com.jpworld.jacinema.admin.service;

import com.jpworld.jacinema.admin.AdminResultCode;
import com.jpworld.jacinema.admin.AdminResultMessage;
import com.jpworld.jacinema.admin.domain.Seat;
import com.jpworld.jacinema.admin.domain.TheaterTime;
import com.jpworld.jacinema.admin.dto.*;
import com.jpworld.jacinema.admin.repository.SeatRepository;
import com.jpworld.jacinema.admin.repository.TheaterTimeRepository;
import com.jpworld.jacinema.exceptions.EntityNotFountException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
@RequiredArgsConstructor
public class SeatService {

    private final SeatRepository seatRepository;
    private final TheaterTimeRepository theaterTimeRepository;

    public SeatResponse findByTheaterTimeId(Long theaterTimeId) {
        Seat findSeat = seatRepository.findByTheaterTimeId(theaterTimeId)
                .orElseThrow(() -> new EntityNotFountException(AdminResultMessage.NOT_FOUND_THEATER_TIME_ID,
                        AdminResultCode.NOT_FOUND_ID_CODE));

        SeatResponseDto seatResponseDto = SeatResponseDto.fromEntity(findSeat);

        return SeatResponse.builder()
                .seat(seatResponseDto)
                .message(AdminResultMessage.SUCCESS)
                .resultCode(AdminResultCode.SUCCESS_CODE)
                .build();
    }

    public SeatResponse addSeat(SeatRequest seatRequest) {
        TheaterTime theaterTime = theaterTimeRepository.findById(seatRequest.getTheaterTimeId())
                .orElseThrow(() -> new EntityNotFountException(AdminResultMessage.NOT_FOUND_THEATER_TIME_ID, AdminResultCode.NOT_FOUND_ID_CODE));

        Seat saveSeat = Seat.builder()
                .totalSeats(seatRequest.getTotalSeats())
                .theaterTime(theaterTime)
                .build();

        seatRepository.save(saveSeat);
        return SeatResponse.builder()
                .message(AdminResultMessage.SUCCESS)
                .resultCode(AdminResultCode.SUCCESS_CODE)
                .build();
    }

    public SeatResponse updateSeat(SeatUpdateRequest seatUpdateRequest) {
        Long seatId = seatUpdateRequest.getSeatId();
        if(seatId == null) {
            throw new EntityNotFountException(AdminResultMessage.NOT_FOUND_SEAT_ID, AdminResultCode.NOT_FOUND_ID_CODE);
        }

        Seat findSeat = seatRepository.findById(seatId).orElseThrow(() ->
                new EntityNotFountException(AdminResultMessage.NOT_FOUND_SEAT_ID, AdminResultCode.NOT_FOUND_ID_CODE));

        TheaterTime theaterTime = theaterTimeRepository.findById(findSeat.getTheaterTime().getId()).orElseThrow(
                () -> new EntityNotFountException(AdminResultMessage.NOT_FOUND_THEATER_TIME_ID, AdminResultCode.NOT_FOUND_ID_CODE)
        );

        findSeat.updateSeat(
                seatUpdateRequest.getTotalSeats() != null ? seatUpdateRequest.getTotalSeats() : findSeat.getTotalSeats(),
                seatUpdateRequest.getReservedSeats() != null ? seatUpdateRequest.getReservedSeats() : findSeat.getReservedSeats(),
                theaterTime
        );

        seatRepository.save(findSeat);

        return SeatResponse.builder()
                .message(AdminResultMessage.SUCCESS)
                .resultCode(AdminResultCode.SUCCESS_CODE)
                .build();
    }

    public SeatResponse deleteSeat(SeatDeleteRequest seatDeleteRequest) {
        Long seatId = seatDeleteRequest.getSeatId();
        if(seatId == null) {
            throw new EntityNotFountException(AdminResultMessage.NOT_FOUND_SEAT_ID, AdminResultCode.NOT_FOUND_ID_CODE);
        }

        if(seatRepository.existsById(seatId)) {
            seatRepository.deleteById(seatId);
            return SeatResponse.builder()
                    .message(AdminResultMessage.SUCCESS)
                    .resultCode(AdminResultCode.SUCCESS_CODE)
                    .build();
        } else {
            throw new EntityNotFountException(AdminResultMessage.NOT_FOUND_SEAT_ID, AdminResultCode.NOT_FOUND_ID_CODE);
        }
    }

    public SeatResponse validationError(BindingResult bindingResult) {
        String errMsg = bindingResult.getFieldError().getDefaultMessage();
        String code = AdminResultMessage.SEAT_REQUIRED_LIST
                .contains(errMsg) ? AdminResultCode.PARAMETER_IS_NULL : AdminResultCode.ERROR_CODE;

        return SeatResponse.builder()
                .message(errMsg)
                .resultCode(code)
                .build();
    }
}
