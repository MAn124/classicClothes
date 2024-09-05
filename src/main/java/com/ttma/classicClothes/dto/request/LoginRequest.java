package com.ttma.classicClothes.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginRequest {
    @NotEmpty(message = "Password is empty")
    private String username;
    @NotEmpty(message = "Password is empty")
    private String password;
    @NotEmpty(message = "Password is empty")
    private String deviceToken;
    @NotEmpty(message = "Password is empty")
    private  String version;
}
