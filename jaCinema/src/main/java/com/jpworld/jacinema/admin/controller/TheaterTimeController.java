package com.jpworld.jacinema.admin.controller;

import com.jpworld.jacinema.admin.domain.Theater;
import com.jpworld.jacinema.admin.dto.*;
import com.jpworld.jacinema.admin.service.TheaterTimeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/theaterTime")
public class TheaterTimeController {

    private final TheaterTimeService theaterTimeService;

    @GetMapping("/list")
    public ResponseEntity<TheaterTimeResponse> list(@RequestParam("theaterId") Long theaterId) {
        TheaterTimeResponse theaterTimeResponse = theaterTimeService.findByTheaterId(theaterId);

        return new ResponseEntity<>(theaterTimeResponse, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<TheaterTimeResponse> addTheaterTime(@Valid @RequestBody TheaterTimeRequest theaterTimeRequest,
                                                              BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            TheaterTimeResponse errorResponse = theaterTimeService.validationError(bindingResult);
            return new ResponseEntity<>(errorResponse, HttpStatus.OK);
        }

        TheaterTimeResponse addTheaterTime = theaterTimeService.addTheaterTime(theaterTimeRequest);
        return new ResponseEntity<>(addTheaterTime, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<TheaterTimeResponse> updateTheaterTime(@RequestBody TheaterTimeUpdateRequest theaterTimeUpdateRequest) {
        TheaterTimeResponse theaterTimeResponse = theaterTimeService.updateTheaterTime(theaterTimeUpdateRequest);
        return new ResponseEntity<>(theaterTimeResponse, HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<TheaterTimeResponse> deleteTheaterTime(@RequestBody TheaterTimeDeleteRequest theaterTimeDeleteRequest) {
        TheaterTimeResponse theaterTimeResponse = theaterTimeService.deleteTheaterTime(theaterTimeDeleteRequest);
        return new ResponseEntity<>(theaterTimeResponse, HttpStatus.OK);
    }
}
