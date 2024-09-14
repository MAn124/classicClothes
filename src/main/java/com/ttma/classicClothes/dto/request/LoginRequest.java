package com.ttma.classicClothes.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequest {
    @NotEmpty(message = "Username is empty")
    private String username;
    @NotEmpty(message = "Password is empty")
    private String password;
}
