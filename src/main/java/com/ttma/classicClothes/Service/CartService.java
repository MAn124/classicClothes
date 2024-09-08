package com.ttma.classicClothes.Service;

import com.ttma.classicClothes.dto.request.CartRequest;
import com.ttma.classicClothes.dto.response.ResponseCart;
import com.ttma.classicClothes.model.Cart;

public interface CartService {
  Cart addToCart(Long userId, Long  productId, Integer quantity);
  ResponseCart getCart(Long userId);

  void removeCart(Long userId);
}
