package com.nanhng.FastFood.dto.request.order;

import com.nanhng.FastFood.entity.order.constants.OrderStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateOrderReq {
    Integer userId;
    Integer orderId;
    OrderStatus status;
    String city;
    String street;
    String name;
    String note;
}
