package com.ttma.classicClothes.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailConfirmRequest {
    private String email;
    private String confirmationCode;
}
