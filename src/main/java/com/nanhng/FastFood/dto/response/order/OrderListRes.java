package com.nanhng.FastFood.dto.response.order;

import com.nanhng.FastFood.entity.order.constants.OrderStatus;
import com.nanhng.FastFood.entity.order.OrderItem;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;
import lombok.experimental.FieldDefaults;


import java.util.Date;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderListRes {
    Integer id;

    List<OrderItem> orderItems;

    Integer userId;
    OrderStatus status;
    String city;
    String street;

    @Temporal(TemporalType.TIMESTAMP)
    Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    Date updatedAt;
    String name;
    String note;
}
