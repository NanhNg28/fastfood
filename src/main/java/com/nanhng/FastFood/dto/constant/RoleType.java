package com.nanhng.FastFood.dto.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(type = "integer")
public enum RoleType implements BaseEnum<Integer> {
    ADMIN(0),
    CUSTOMER(1),
    EMPLOYEE(2);



    private final int value;

    RoleType(int value) {
        this.value = value;
    }

    @JsonCreator
    public static RoleType fromValue(int value) {
        for(RoleType roleType : RoleType.values()) {
            if(roleType.toValue() == value){
                return roleType;
            }
        }
        return null;
    }

    @JsonValue
    public Integer toValue() {
        return value;
    }
}
