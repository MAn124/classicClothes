package com.ttma.classicClothes.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
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
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "quantity")
    @Positive(message = "product quantity must be > 0")
    private Integer quantity;
    @Column(name = "price")
    @Positive(message = "product price must be > 0")
    private BigDecimal price;
    @Column(name = "product_img")
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
