package com.ttma.classicClothes.Service;

import com.ttma.classicClothes.dto.response.ResponseCart;

public interface CartService {
  long addToCart(Long userId, Long  productId, Integer quantity);
  ResponseCart getCart(Long userId);

  void removeCart(Long userId);
}
