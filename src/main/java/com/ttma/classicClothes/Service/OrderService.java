package com.ttma.classicClothes.Service;

import com.ttma.classicClothes.dto.request.OrderRequest;
import com.ttma.classicClothes.dto.response.ResponseOrder;
import com.ttma.classicClothes.enums.OrderStatus;
import com.ttma.classicClothes.model.Orders;

import java.util.List;

public interface OrderService {
    long createOrder(long userId, String address, String number);
    List<ResponseOrder> getAllOrder(int pageNo,int pageSize);
    List<Orders> getUserOrders(long userId);
    Orders updateOrder(long orderId, OrderStatus status);
}
