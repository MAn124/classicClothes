package com.ttma.classicClothes.Service.Impl;

import com.ttma.classicClothes.Service.EmailService;
import com.ttma.classicClothes.Service.OrderService;
import com.ttma.classicClothes.enums.OrderStatus;
import com.ttma.classicClothes.model.*;
import com.ttma.classicClothes.repository.CartRepository;
import com.ttma.classicClothes.repository.OrderRepository;
import com.ttma.classicClothes.repository.ProductRepository;
import com.ttma.classicClothes.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final EmailService emailService;
    @Override
    public long createOrder(long userId, String address, String number) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Cart cart = cartRepository.findByUserId(userId).orElseThrow(() ->new RuntimeException("User not found"));
       if( cart.getCartItems().isEmpty()){
           throw new IllegalStateException("");
        }

        Orders orders =new Orders();
        orders.setUser(user);
        orders.setAddress(address);
        orders.setPhoneNumber(number);
        List<OrderItems> orderItems = createOrderItems(cart,orders);
        orders.setOrderItems(orderItems);
        Orders saveOrder = orderRepository.save(orders);
        cartRepository.deleteById(userId);
        try{
            emailService.sendOrderConfirm(saveOrder);
        }catch (Exception e){
        log.error("Failed to send email confirm to order ID:" + saveOrder.getId());
        }
        return orders.getId();
    }
    private List<OrderItems> createOrderItems(Cart cart, Orders orders){
        return cart.getCartItems().stream().map(cartItems ->{
            Product product = productRepository.findById(cartItems.getProduct().getId()).orElseThrow(() -> new RuntimeException("Product not found"));
            if(product.getQuantity() == null){
                throw new IllegalStateException("");
            }
            if(product.getQuantity() < cartItems.getQuantity()){
                throw new RuntimeException("");
            }
            product.setQuantity(product.getQuantity() - cartItems.getQuantity());
            productRepository.save(product);
            return new OrderItems(cartItems.getQuantity(), product.getPrice(), orders, product);
        } ).toList();
    }
    public List<Orders> getAllOrder(){
        return orderRepository.findAll();
    }
    public List<Orders> getUserOrders(long userId){
        return orderRepository.findByUserId(userId);
    }
    public Orders updateOrder(long orderId, OrderStatus status){
        Orders orders = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        orders.setStatus(status);
        return orderRepository.save(orders);
    }
}
