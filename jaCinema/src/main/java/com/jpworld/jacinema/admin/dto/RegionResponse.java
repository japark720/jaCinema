package com.jpworld.jacinema.admin.dto;

import com.jpworld.jacinema.admin.domain.Region;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class RegionResponse {

    private Long id;
    private String name;
    private List<RegionResponse> children;

    public RegionResponse(Long id, String name, List<RegionResponse> children) {
        this.id = id;
        this.name = name;
        this.children = children != null ? children : new ArrayList<>();
    }

    public static RegionResponse from(Region region) {
        List<RegionResponse> childResponses = region.getChildren().stream()
                .map(child -> new RegionResponse(child.getId(), child.getName(), null))
                .collect(Collectors.toList());

        return new RegionResponse(region.getId(), region.getName(), childResponses);
    }

}

