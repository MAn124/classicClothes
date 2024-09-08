package com.ttma.classicClothes.dto.request;

import com.ttma.classicClothes.model.Category;
import com.ttma.classicClothes.model.Comment;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class ProductRequest {
    private String name;
    private String description;
    private int quantity;
    private BigDecimal price;
    private String image;
    private Long categoryId;
    private List<Comment> comments;
}
