package com.ttma.classicClothes.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseOrder {
    private String address;
    private String phoneNumber;
}
