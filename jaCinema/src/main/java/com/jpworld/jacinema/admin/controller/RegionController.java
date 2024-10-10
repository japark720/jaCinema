package com.jpworld.jacinema.admin.controller;

import com.jpworld.jacinema.admin.domain.Region;
import com.jpworld.jacinema.admin.dto.RegionRequest;
import com.jpworld.jacinema.admin.dto.RegionResponse;
import com.jpworld.jacinema.admin.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("admin/region")
public class RegionController {

    private final RegionService regionService;

    @GetMapping("/list")
    public ResponseEntity<?> list() {
        List<RegionResponse> regions = regionService.findAllParentRegions();
        return new ResponseEntity<>(regions, HttpStatus.OK);
    }

    @PostMapping("/add/region")
    public ResponseEntity<?> add(@RequestBody RegionRequest regionRequest) {
        RegionResponse region = regionService.createRegion(regionRequest);
        return new ResponseEntity<>(region, HttpStatus.OK);
    }
}
