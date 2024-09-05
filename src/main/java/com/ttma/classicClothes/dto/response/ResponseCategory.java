package com.ttma.classicClothes.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseCategory {
    private Long id;
    private String name;
    private String description;
}
