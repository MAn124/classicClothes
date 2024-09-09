package com.ttma.classicClothes.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentRequest {
    private String content;
    private Integer Score;

}
