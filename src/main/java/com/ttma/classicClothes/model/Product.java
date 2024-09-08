package com.ttma.classicClothes.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Entity
@Table(name = "product")
public class Product extends AbstractEntity<Long> implements Serializable {
    private String name;
    private String description;
    private Integer quantity;
    private BigDecimal price;
    private String image;

    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;
    @OneToMany(mappedBy = "product")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<OrderItems> orderItems = new ArrayList<>();
    @OneToMany(mappedBy = "product")
    private List<CartItems> cartItems = new ArrayList<>();
}
