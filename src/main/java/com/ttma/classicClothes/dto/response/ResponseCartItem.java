package com.ttma.classicClothes.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseCartItem {
    private Long id;
    private Long productId;
    private int quantity;
}
