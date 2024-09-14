package com.ttma.classicClothes.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class ResponseOrderItem {
    private Long id;
    private Long productId;
    private int quantity;
    private BigDecimal price;
}
