package com.ttma.classicClothes.dto.response;

import com.ttma.classicClothes.enums.OrderStatus;
import com.ttma.classicClothes.model.OrderItems;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ResponseOrder {
    private Long id;
    private Long userId;
    private String address;
    private String phoneNumber;
    private OrderStatus status;
    private List<ResponseOrderItem> orderItems;
}
