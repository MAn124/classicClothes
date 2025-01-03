package com.ttma.classicClothes.dto.response;

import com.ttma.classicClothes.enums.RoleEnum;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseUser {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private RoleEnum role;
    private boolean emailConfirmation;
    private String confirmCode;
}
