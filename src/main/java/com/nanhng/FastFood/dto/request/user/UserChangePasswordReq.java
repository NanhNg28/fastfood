package com.nanhng.FastFood.dto.request.user;

import jakarta.validation.constraints.AssertTrue;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserChangePasswordReq {
    @NotNull
    Integer userId;
    @NotNull
    String oldPassword;
    @NotNull
    String newPassword;
    @NotNull
    String confirmPassword;

    @AssertTrue(message = " mật khẩu và mật khẩu xác nhận phải giống nhau")
    public boolean isPasswordMatch() {
        return newPassword.equals(confirmPassword);
    }
}
