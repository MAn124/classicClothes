package com.ttma.classicClothes.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChangePasswordRequest {
    private String currentPassword;
    private String newPassword;
}
