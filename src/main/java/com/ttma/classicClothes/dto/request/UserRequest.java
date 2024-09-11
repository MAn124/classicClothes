package com.ttma.classicClothes.dto.request;

import com.ttma.classicClothes.enums.RoleEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {
    @NotEmpty(message = "First Name is empty")
    @Size(min = 6, max = 50, message = "At least 6 characters and maximum 100 characters")
    private String firstName;
    @NotEmpty(message = "Last Name is empty")
    @Size(min = 6, max = 50, message = "At least 6 characters and maximum 100 characters")
    private String lastName;
    @NotEmpty(message = "Email is empty")
    @Size(min = 6, max = 50, message = "At least 6 characters and maximum 100 characters")
    @Email(message = "invalid email form")
    private String email;
    @NotEmpty(message = "Username is empty")
    @Size(min = 6, max = 50, message = "At least 6 characters and maximum 100 characters")
    private String username;
    @NotEmpty(message = "Password is empty")
    @Size(min = 6, max = 50, message = "At least 6 characters and maximum 100 characters")
    private String password;
    private RoleEnum role;
}
