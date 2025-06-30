package com.nanhng.FastFood.entity.order.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.nanhng.FastFood.dto.constant.BaseEnum;
import com.nanhng.FastFood.entity.transaction.constants.PaymentStatus;

public enum PaymentGateway implements BaseEnum<String> {
    MANUAL;
    @JsonValue
    public String toValue() {
        return this.toString();
    }

    @JsonCreator
    public static PaymentGateway fromValue(String v) {
        for(PaymentGateway c: PaymentGateway.values()) {
            if(c.toValue().equalsIgnoreCase(v)) {
                return c;
            }
        }
        return null;
    }
}
