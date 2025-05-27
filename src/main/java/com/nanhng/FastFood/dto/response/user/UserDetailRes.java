package com.nanhng.FastFood.dto.response.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nanhng.FastFood.dto.constant.ActiveStatus;
import com.nanhng.FastFood.dto.constant.RoleType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDetailRes {
    Integer id;
    String username;
    String phone;
    String email;
    ActiveStatus status;
    RoleType role;
    String city;
    String street;
    Integer addressId;
    String authToken;
}
