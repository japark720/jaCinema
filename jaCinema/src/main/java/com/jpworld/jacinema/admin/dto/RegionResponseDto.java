package com.jpworld.jacinema.admin.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jpworld.jacinema.admin.domain.Region;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class RegionResponseDto {

    private Long id;
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long parentId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<RegionResponseDto> children;

    // 부모 Region을 위한 변환 메소드
    public static RegionResponseDto fromParentEntity(Region region) {
        return RegionResponseDto.builder()
                .id(region.getId())
                .name(region.getName())
                .children(region.getChildren().stream()
                        .map(RegionResponseDto::fromChildEntity)
                        .toList()) // 자식들은 childEntity로 변환
                .build();
    }

    // 자식 Region을 위한 변환 메소드 (children과 parentId만 포함)
    public static RegionResponseDto fromChildEntity(Region region) {
        return RegionResponseDto.builder()
                .id(region.getId())
                .name(region.getName())
                .parentId(region.getParent() != null ? region.getParent().getId() : null)
                .build(); // children 필드 제외
    }

    public static RegionResponseDto fromEntity(Region region) {
        return RegionResponseDto.builder()
                .id(region.getId())
                .name(region.getName())
                .parentId(region.getParent() != null ? region.getParent().getId() : null)
                .build();
    }

}
