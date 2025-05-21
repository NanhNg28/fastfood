package com.nanhng.FastFood.dto.response.order;

import com.nanhng.FastFood.dto.constant.OrderStatus;
import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddOrderRes {
    Integer id;
    Double totalPrice;
    Integer userId;
    OrderStatus status;
    String city;
    String street;
}
