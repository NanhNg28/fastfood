package com.nanhng.FastFood.dto.request.user;

import com.nanhng.FastFood.dto.constant.RoleType;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddUserReq {
    @NotBlank(message = "username can't be blank")
    String username;
    @NotBlank(message = "password can't be blank")
    String password;
    @NotBlank
    String confirmPassword;
    String email;
    @NotBlank(message = "phone number can't be blank")
    String phone;

    RoleType role;

    @NotNull
    String city;

    @NotNull
    String street;

    @AssertTrue(message = "password don't match")
    public boolean isValidPassword() {
        return password.equals(confirmPassword);
    }
}
