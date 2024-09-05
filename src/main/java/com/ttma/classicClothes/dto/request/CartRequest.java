package com.ttma.classicClothes.dto.request;

import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class CartRequest {
    private Long id;
    private Long userId;
    private List<CartItemsRequest> items;

}
