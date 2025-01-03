package com.ttma.classicClothes.dto.request;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class OrderItemsRequest {
    private int productId;
    private int quantity;
    private BigDecimal price;
}
