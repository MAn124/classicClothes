package com.ttma.classicClothes.Service.Impl;

import com.ttma.classicClothes.Service.CartService;
import com.ttma.classicClothes.dto.response.ResponseCart;
import com.ttma.classicClothes.model.Cart;
import com.ttma.classicClothes.model.CartItems;
import com.ttma.classicClothes.model.Product;
import com.ttma.classicClothes.model.User;
import com.ttma.classicClothes.repository.CartRepository;
import com.ttma.classicClothes.repository.ProductRepository;
import com.ttma.classicClothes.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    @Override
    public long addToCart(Long userId, Long productId, Integer quantity) {
        //kiem tra nguoi dung
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        //kiem tra san pham
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        //kiem tra soluong san pham
        if(product.getQuantity() < quantity){
            throw new RuntimeException("Not enough available");
        }
        //kiem tra gio hang
        Cart cart = cartRepository.findByUserId(userId).orElse(new Cart(null, user, new ArrayList<>()));
        //kiem tra trong gio hang co san pham hay khong
        Optional<CartItems> existItems = cart.getCartItems().stream().filter(cartItems -> cartItems.getProduct().getId().equals(productId)).findFirst();
        if(existItems.isPresent()){
            CartItems cartItems = existItems.get();
            cartItems.setQuantity(cartItems.getQuantity()+quantity);
        } else {
            CartItems cartItems = new CartItems(null, cart, product, quantity);
            cart.getCartItems().add(cartItems);
        }
        cartRepository.save(cart);
        return cart.getId();
    }

    @Override
    public ResponseCart getCart(Long userId) {
        Cart cart = getByUserId(userId);
        return ResponseCart.builder()
                .id(cart.getId())
                .userId(cart.getUser().getId())
                .build();
    }

    @Override
    public void removeCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("User not found"));
        cart.getCartItems().clear();
        cartRepository.save(cart);
    }
    private Cart getByUserId(long id){
        return cartRepository.findByUserId(id).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
