package com.nanhng.FastFood.entity.order.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.nanhng.FastFood.dto.constant.BaseEnum;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum OrderStatus implements BaseEnum<String> {
    PENDING,
    DELIVERED,
    CANCELLED;

    @JsonValue
    public String toValue() {
        return toString();
    }

    @JsonCreator
    public static OrderStatus fromValue(String v) {
        for(OrderStatus c: OrderStatus.values()) {
            if(c.toValue().equalsIgnoreCase(v)) {
                return c;
            }
        }
        return null;
    }

}
