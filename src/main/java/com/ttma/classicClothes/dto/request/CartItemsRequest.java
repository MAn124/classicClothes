package com.ttma.classicClothes.dto.request;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class CartItemsRequest {
    private Long id;
    private Long productId;
    private int quantity;
}
