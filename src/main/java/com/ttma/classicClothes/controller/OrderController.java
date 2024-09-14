package com.ttma.classicClothes.controller;

import com.ttma.classicClothes.Service.OrderService;
import com.ttma.classicClothes.dto.response.ResponseData;
import com.ttma.classicClothes.dto.response.ResponseError;
import com.ttma.classicClothes.enums.OrderStatus;
import com.ttma.classicClothes.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @PostMapping("/")
    @PreAuthorize("isAuthenticated()")
    public ResponseData<?> createOrder(@AuthenticationPrincipal UserDetails userDetails,
                                       @RequestParam String address,
                                       @RequestParam String number){
        try {
            Long userId = ((User) userDetails).getId();
            return new ResponseData<>(HttpStatus.CREATED.value(), "sucess",orderService.createOrder(userId,address,number));
        }catch (Exception e){
            log.error("error message : {}",e.getMessage(),e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Failed");
        }

    }
    @GetMapping("/")
    public ResponseData<?> getAllOrders(@RequestParam(defaultValue = "0", required = false) int pageNo,
                                        @RequestParam(defaultValue = "20", required = false) int pageSize){
        try{
            return new ResponseData<>(HttpStatus.OK.value(), "success",orderService.getAllOrder(pageNo,pageSize));
        } catch (Exception e){
            log.error("error message : {}",e.getMessage(),e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Failed");
        }
    }
    @PutMapping("/{id}/status")
    public ResponseData<?> updateOrder(@PathVariable("id")long id,
                                       @RequestParam OrderStatus status){
        try{
            return new ResponseData<>(HttpStatus.OK.value(), "success",orderService.updateOrder(id, status));
        } catch (Exception e){
            log.error("error message : {}",e.getMessage(),e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Failed");
        }
    }
}
