package com.jpworld.jacinema.admin.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jpworld.jacinema.admin.dto.RegionRequest;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@RequiredArgsConstructor
public class Region {

    @Id @GeneratedValue
    @Column(name = "region_id")
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_region_id")
    @JsonIgnore // parent와 children 필드가 서로를 참조하고 있으므로, JSON으로 직렬화 할 때 서로를 계속 참조하여 무한호출하는 문제 발생
    private Region parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Region> children = new ArrayList<>();

    @Builder
    public Region(RegionRequest regionRequest, Region parent) {
        this.name = regionRequest.getName();
        this.parent = parent;
    }

    public void addChildren(Region child) {
        this.children.add(child);
        child.setParent(this);
    }

    public void setParent(Region parent) {
        this.parent = parent;
    }

}
