package com.nanhng.FastFood.dto.response.order;

import com.nanhng.FastFood.entity.order.constants.OrderStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddOrderRes {
    Integer id;
    Integer userId;
    OrderStatus status;
    String city;
    String street;
    String name;
    String note;
}
