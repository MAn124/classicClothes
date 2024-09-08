package com.ttma.classicClothes.dto.response;

import com.ttma.classicClothes.model.CartItems;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ResponseCart {
    private Long id;
    private Long userId;
    private List<CartItems>cartItems;
}
