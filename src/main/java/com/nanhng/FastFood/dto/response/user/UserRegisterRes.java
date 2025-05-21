package com.nanhng.FastFood.dto.response.user;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.lang.NonNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRegisterRes {
    @NonNull
    String username;
    String email;
    String phone;


}
