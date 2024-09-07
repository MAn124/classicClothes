package com.ttma.classicClothes.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseToken {
    private String accessToken;
    private String refreshToken;
    private Long id;
}
