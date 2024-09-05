package com.ttma.classicClothes.model;

import com.ttma.classicClothes.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Entity
@Table(name = "orders")
public class Orders extends AbstractEntity<Long> implements Serializable {
    private String address;
    private String phoneNumber;
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;


    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItems> orderItems = new ArrayList<>();
}
