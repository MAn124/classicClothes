package com.ttma.classicClothes.dto.request;

import com.ttma.classicClothes.enums.OrderStatus;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class OrderRequest {
    private Long userId;
    private String address;
    private String phoneNumber;
    private OrderStatus status;
    private List<OrderItemsRequest> orderItems;
}
