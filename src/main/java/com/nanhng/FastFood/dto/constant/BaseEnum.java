package com.nanhng.FastFood.dto.constant;

import com.fasterxml.jackson.annotation.JsonValue;

public interface BaseEnum<T> {
    @JsonValue
    T toValue();
}
