package com.nanhng.FastFood.entity.otp.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.nanhng.FastFood.dto.constant.BaseEnum;

public enum OtpType implements BaseEnum<Integer> {
    ZNS(0),
    EMAIL(1),
    SMS(2);

    final int value;

    OtpType(int value) {
        this.value = value;
    }

    @JsonCreator
    public static OtpType fromValue(int value) {
        for (OtpType column : OtpType.values()) {
            if (column.value == value) {
                return column;
            }
        }
        return null;
    }

    @JsonValue
    public Integer toValue() {
        return value;
    }
}
