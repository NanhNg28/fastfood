package com.nanhng.FastFood.dto.response.user;

import com.nanhng.FastFood.dto.constant.ActiveStatus;
import com.nanhng.FastFood.dto.constant.RoleType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserListRes {
    String username;
    String email;
    String phone;
    ActiveStatus status;
    RoleType role;
    Integer addressId;
    int id;
}
