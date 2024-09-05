package com.ttma.classicClothes.Service.Impl;

import com.ttma.classicClothes.Service.CartService;
import com.ttma.classicClothes.dto.request.CartRequest;
import com.ttma.classicClothes.model.Cart;
import com.ttma.classicClothes.model.CartItems;
import com.ttma.classicClothes.model.Product;
import com.ttma.classicClothes.model.User;
import com.ttma.classicClothes.repository.CartRepository;
import com.ttma.classicClothes.repository.ProductRepository;
import com.ttma.classicClothes.repository.UserRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final ProductRepository productRepository;
    private final UserRespository userRespository;
    private final CartRepository cartRepository;
    @Override
    public Cart addToCart(Long userId, Long productId, Integer quantity) {
        User user = userRespository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        if(product.getQuantity() < quantity){
            throw new RuntimeException("Not enough available");
        }
        Cart cart = cartRepository.findByUserId(userId).orElse(new Cart(null, user, new ArrayList<>()));
        Optional<CartItems> existItems = cart.getCartItems().stream().filter(cartItems -> cartItems.getProduct().getId().equals(productId)).findFirst();
        if(existItems.isPresent()){
            CartItems cartItems = existItems.get();
            cartItems.setQuantity(cartItems.getQuantity()+quantity);
        } else {
            CartItems cartItems = new CartItems(null, cart, product, quantity);
            cart.getCartItems().add(cartItems);
        }
        return cartRepository.save(cart);
    }
}
