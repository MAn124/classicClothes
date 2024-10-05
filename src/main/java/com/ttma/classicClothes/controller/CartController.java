package com.ttma.classicClothes.controller;

import com.ttma.classicClothes.Service.CartService;
import com.ttma.classicClothes.dto.response.ResponseData;
import com.ttma.classicClothes.dto.response.ResponseError;
import com.ttma.classicClothes.model.User;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    @Operation(summary = "Create cart ", description = "Add product to cart")
    @PostMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public ResponseData<?> addToCart(@AuthenticationPrincipal UserDetails userDetails,
                                     @RequestParam Long productId,
                                     @RequestParam Integer quantity){
        try{
            Long userId = ((User) userDetails).getId();
            return new ResponseData<>(HttpStatus.CREATED.value(),"success",cartService.addToCart(userId,productId,quantity));
        }catch (Exception e){
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Failed");
        }
    };
    @Operation(summary = "Get cart by id", description = "Get cart  by id")
    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseData<?> getCart(@AuthenticationPrincipal UserDetails userDetails){
        try{
            Long userId = ((User) userDetails).getId();
            return new ResponseData<>(HttpStatus.OK.value(),"success",cartService.getCart(userId));
        }catch (Exception e){
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Failed");
        }
    };
}
