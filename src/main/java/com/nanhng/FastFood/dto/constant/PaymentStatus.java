package com.nanhng.FastFood.dto.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PaymentStatus implements BaseEnum<String> {
    PENDING,
    PAID,
    FAILED,
    REFUNDED;

    @JsonValue
    public String toValue() {
        return toString();
    }

    @JsonCreator
    public static PaymentStatus fromValue(String v) {
        for(PaymentStatus c: PaymentStatus.values()) {
            if(c.toValue().equalsIgnoreCase(v)) {
                return c;
            }
        }
        return null;
    }

}
