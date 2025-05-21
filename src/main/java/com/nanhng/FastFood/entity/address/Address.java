package com.nanhng.FastFood.entity.address;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nanhng.FastFood.dto.constant.ActiveStatus;
import com.nanhng.FastFood.entity.BaseEntity;
import com.nanhng.FastFood.entity.user.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Address extends BaseEntity {
    @NotNull
    String city;
    @NotNull
    String street;

    @Column(name = "status",columnDefinition = "INT")
    ActiveStatus status;
}
