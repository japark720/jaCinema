package com.jpworld.jacinema.admin.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jpworld.jacinema.admin.domain.Region;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class RegionResponse {
    private String message;
    private String resultCode;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<RegionResponseDto> regions;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private RegionResponseDto region;
}
