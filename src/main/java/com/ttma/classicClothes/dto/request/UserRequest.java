package com.ttma.classicClothes.dto.request;

import com.ttma.classicClothes.enums.RoleEnum;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {
    private String firstName;
    private String lastName;
    private String email;
    @NotEmpty(message = "username is empty")
    private String username;
    private String password;
    private RoleEnum role;
}
