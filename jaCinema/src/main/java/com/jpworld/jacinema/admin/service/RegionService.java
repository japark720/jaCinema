package com.jpworld.jacinema.admin.service;

import com.jpworld.jacinema.admin.domain.Region;
import com.jpworld.jacinema.admin.dto.RegionRequest;
import com.jpworld.jacinema.admin.dto.RegionResponse;
import com.jpworld.jacinema.admin.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegionService {

    private final RegionRepository regionRepository;

    public List<RegionResponse> findAll() {
        List<Region> regions = regionRepository.findAll();

        return regions.stream()
                .map(RegionResponse::from)
                .collect(Collectors.toList());
    }

    public RegionResponse findById(Long id) {
        Region region = regionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Region not fount with id: " + id));

        return RegionResponse.from(region);
    }

    public List<RegionResponse> findAllParentRegions() {
        List<Region> regions = regionRepository.findAll();

        // 부모 지역만 필터링
        List<Region> parentRegions = regions.stream()
                .filter(region -> region.getParent() == null) // 부모가 없는 지역만 포함
                .collect(Collectors.toList());

        return parentRegions.stream()
                .map(RegionResponse::from)
                .collect(Collectors.toList());
    }

    public RegionResponse createRegion(RegionRequest regionRequest) {
        Region parentRegion = null;

        if (regionRequest.getParentId() != null) {
            parentRegion = regionRepository.findById(regionRequest.getParentId())
                    .orElseThrow(() -> new IllegalArgumentException("Parent region not found"));
        }

        Region newRegion = Region.builder()
                .regionRequest(regionRequest)
                .parent(parentRegion)
                .build();

        Region saveRegion = regionRepository.save(newRegion);
        return RegionResponse.from(saveRegion);
    }
}
