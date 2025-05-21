package com.nanhng.FastFood.dto.request.user;

import com.nanhng.FastFood.dto.constant.RoleType;
import com.nanhng.FastFood.entity.role.Role;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRegisterReq {

    @NotBlank
    String username;
    @NotBlank
    String password;
    @NotBlank
    String confirmPassword;
    String email;
    @NotBlank
    String phone;
    @NotNull
    RoleType role;

    @NotBlank
    String city;
    @NotBlank
    String street;

    @AssertTrue(message = "password don't matching")
    public boolean isValidPassword() {
        return password.equals(confirmPassword);
    }
}
