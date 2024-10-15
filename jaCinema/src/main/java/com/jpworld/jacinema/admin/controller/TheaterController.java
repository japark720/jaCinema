package com.jpworld.jacinema.admin.controller;

import com.jpworld.jacinema.admin.dto.TheaterRequest;
import com.jpworld.jacinema.admin.dto.TheaterResponse;
import com.jpworld.jacinema.admin.dto.TheaterUpdateRequest;
import com.jpworld.jacinema.admin.service.TheaterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/theater")
public class TheaterController {

    private final TheaterService theaterService;

    @GetMapping("/list")
    public ResponseEntity<?> list() {
        TheaterResponse theater = theaterService.findAll();
        return new ResponseEntity<>(theater, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<TheaterResponse> addTheater(@Valid @RequestBody TheaterRequest theaterRequest, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            TheaterResponse errorResponse = theaterService.validationError(bindingResult);
            return new ResponseEntity<>(errorResponse, HttpStatus.OK);
        }

        TheaterResponse addTheater = theaterService.addTheater(theaterRequest);
        return new ResponseEntity<>(addTheater, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<TheaterResponse> updateTheater(@Valid @RequestBody TheaterUpdateRequest theaterUpdateRequest) {
        TheaterResponse updateTheater = theaterService.updateTheater(theaterUpdateRequest);
        return new ResponseEntity<>(updateTheater, HttpStatus.OK);
    }


    @PostMapping("/delete")
    public ResponseEntity<TheaterResponse> deleteTheater(@RequestBody TheaterRequest theaterRequest) {
        TheaterResponse result = theaterService.deleteTheater(theaterRequest.getTheaterId());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
