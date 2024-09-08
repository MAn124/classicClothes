package com.ttma.classicClothes.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseComment {
    private Long id;
    private String content;
    private Integer score;
    private Long userId;
}
