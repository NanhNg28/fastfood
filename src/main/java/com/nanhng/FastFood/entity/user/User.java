package com.nanhng.FastFood.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.nanhng.FastFood.dto.constant.ActiveStatus;
import com.nanhng.FastFood.dto.constant.RoleType;
import com.nanhng.FastFood.entity.BaseEntity;
import com.nanhng.FastFood.entity.address.Address;
import com.nanhng.FastFood.entity.role.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.lang.NonNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User extends BaseEntity {

    @NotNull
    String username;

    @JsonIgnore
    @NotNull
    String password;

    String email;

    @NotNull
    String phone;

    @Column(name = "status",columnDefinition = "INT")
    ActiveStatus status;

    @Column(name = "role",columnDefinition = "INT")
    RoleType role;

   @Column(name = "address_id")
   Integer addressId;

   boolean deleted;
}
