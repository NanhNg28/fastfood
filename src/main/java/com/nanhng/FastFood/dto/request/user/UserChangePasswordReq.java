package com.nanhng.FastFood.dto.request.user;

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
}
