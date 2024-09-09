package com.ttma.classicClothes.dto.response;

import com.ttma.classicClothes.model.Category;
import com.ttma.classicClothes.model.Comment;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
public class ResponseProduct {
    private String name;
    private String description;
    private int quantity;
    private BigDecimal price;
    private String image;
    private Long categoryId;
}
