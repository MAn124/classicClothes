package com.ttma.classicClothes.dto.request;

import com.ttma.classicClothes.model.Category;
import com.ttma.classicClothes.model.Comment;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class ProductRequest {
    @NotEmpty(message = "Product name is empty")
    @Size(min = 6, max = 100, message = "At least 6 characters and maximum 100 characters")
    private String name;
    @NotEmpty(message = "First Name is empty")
    @Size(min = 6, max = 100, message = "At least 6 characters and maximum 100 characters")
    private String description;
    @NotEmpty(message = "Quantity is empty")
    private Integer quantity;
    @NotEmpty(message = "Price is empty")
    private BigDecimal price;
    @NotEmpty(message = "First Name is empty")
    private String image;
    private Long categoryId;
}
