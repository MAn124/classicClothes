package com.ttma.classicClothes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Entity
public class OrderItems extends AbstractEntity<Long> implements Serializable {
    private int quantity;
    private BigDecimal price;

    @ManyToOne()
    @JoinColumn(name = "order_id",nullable = false)
    private Orders order;

    @ManyToOne()
    @JoinColumn(name = "product_id",nullable = false)
    private Product product;

}
