package com.jpworld.jacinema.admin.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegionRequest {

    private String name;
    private Long parentId;

}
