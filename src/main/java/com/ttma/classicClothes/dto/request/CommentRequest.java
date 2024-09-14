package com.ttma.classicClothes.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentRequest {
    @NotEmpty(message = "Content is empty")
    @Size(min = 1, max = 200, message = "At least 1 characters and maximum 200 characters")
    private String content;
    @Positive
    @Size(min =1 ,max = 5)
    private Integer Score;

}
