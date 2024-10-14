package com.jpworld.jacinema.admin.service;

import com.jpworld.jacinema.admin.AdminResultCode;
import com.jpworld.jacinema.admin.AdminResultMessage;
import com.jpworld.jacinema.admin.domain.Region;
import com.jpworld.jacinema.admin.dto.RegionRequest;
import com.jpworld.jacinema.admin.dto.RegionResponse;
import com.jpworld.jacinema.admin.dto.RegionResponseDto;
import com.jpworld.jacinema.admin.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegionService {

    private final RegionRepository regionRepository;

    public RegionResponse findAll() {
        List<Region> regions = regionRepository.findAll();

        List<RegionResponseDto> list = regions.stream()
                .filter(region -> region.getParent() == null)
                .map(RegionResponseDto::fromParentEntity)
                .collect(Collectors.toList());

        return RegionResponse.builder()
                .regions(list)
                .message(AdminResultMessage.SUCCESS)
                .resultCode(AdminResultCode.SUCCESS_CODE)
                .build();

    }

    public RegionResponse findById(Long id) {
        Optional<Region> findRegion = regionRepository.findById(id);

        if (findRegion.isPresent()) {
            Optional<RegionResponseDto> regionResponseDto = findRegion.map(RegionResponseDto::fromEntity);
            return RegionResponse.builder()
                    .region(regionResponseDto.get())
                    .message(AdminResultMessage.SUCCESS)
                    .resultCode(AdminResultCode.SUCCESS_CODE)
                    .build();
        }
        return RegionResponse.builder()
                .message(AdminResultMessage.NOT_FOUND)
                .resultCode(AdminResultCode.NOT_FOUND_CODE)
                .build();
    }

    public RegionResponse addRegion(RegionRequest regionRequest) {
        Region parent = null;
        if (regionRequest.getParentId() != null) {
            // 부모 Region이 있으면 조회해서 설정
            parent = regionRepository.findById(regionRequest.getParentId())
                    .orElseThrow(() -> new IllegalArgumentException("parent region not found"));
        }

        Region addRegion = Region.builder()
                .regionRequest(regionRequest)
                .parent(parent)     // 부모 설정
                .build();

        // children 추가
        if (parent != null) {
            parent.addChildren(addRegion);
        }

        regionRepository.save(addRegion);

        return RegionResponse.builder()
                .message(AdminResultMessage.SUCCESS)
                .resultCode(AdminResultCode.SUCCESS_CODE)
                .build();
    }
}