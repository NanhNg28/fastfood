package com.nanhng.FastFood.dto.request.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nanhng.FastFood.dto.constant.OrderStatus;
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
}
