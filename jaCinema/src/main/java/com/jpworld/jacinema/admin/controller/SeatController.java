package com.jpworld.jacinema.admin.controller;

import com.jpworld.jacinema.admin.dto.SeatDeleteRequest;
import com.jpworld.jacinema.admin.dto.SeatRequest;
import com.jpworld.jacinema.admin.dto.SeatResponse;
import com.jpworld.jacinema.admin.dto.SeatUpdateRequest;
import com.jpworld.jacinema.admin.service.SeatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/seat")
public class SeatController {

    private final SeatService seatService;

    @GetMapping("/list")
    public ResponseEntity<SeatResponse> list(@RequestParam("theaterTimeId") Long theaterTimeId) {
        SeatResponse seatResponse = seatService.findByTheaterTimeId(theaterTimeId);
        return new ResponseEntity<>(seatResponse, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<SeatResponse> add(@Valid @RequestBody SeatRequest seatRequest, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            SeatResponse errorResponse = seatService.validationError(bindingResult);
            return new ResponseEntity<>(errorResponse, HttpStatus.OK);
        }

        SeatResponse seatResponse = seatService.addSeat(seatRequest);
        return new ResponseEntity<>(seatResponse, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<SeatResponse> update(@RequestBody SeatUpdateRequest seatUpdateRequest) {
        SeatResponse seatResponse = seatService.updateSeat(seatUpdateRequest);
        return new ResponseEntity<>(seatResponse, HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<SeatResponse> delete(@RequestBody SeatDeleteRequest seatDeleteRequest) {
        SeatResponse seatResponse = seatService.deleteSeat(seatDeleteRequest);
        return new ResponseEntity<>(seatResponse, HttpStatus.OK);
    }
}
