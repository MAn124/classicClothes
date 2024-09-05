package com.ttma.classicClothes.dto.request;

import com.ttma.classicClothes.enums.RoleEnum;
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
    private String username;
    private String password;
    private RoleEnum role;
}
