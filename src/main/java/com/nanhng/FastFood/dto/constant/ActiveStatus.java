package com.nanhng.FastFood.dto.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(type = "integer")
public enum ActiveStatus implements BaseEnum<Integer> {
    INACTIVE(0),
    ACTIVE(1);

    final int value;

    ActiveStatus(int value) {
        this.value = value;
    }

    @JsonValue
    public Integer toValue() {
        return value;
    }

    @JsonCreator
    public static ActiveStatus forValue(int value) {
        for(ActiveStatus status : values()) {
            if(status.toValue() == value){
                return status;
            }
        }
        return null;
    }
}
