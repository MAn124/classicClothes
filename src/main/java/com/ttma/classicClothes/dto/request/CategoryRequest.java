package com.ttma.classicClothes.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryRequest {
    @NotEmpty(message = "Category name is empty")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9]*",message = "special character is not allow")
    private String name;
    @NotEmpty(message = "Description is empty")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9]*",message = "special character is not allow")
    private String description;
}
