package com.nanhng.FastFood.entity.role.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.nanhng.FastFood.dto.constant.BaseEnum;

public enum PermissionType implements BaseEnum<String> {
    DASHBOARD,
    ACCOUNT,
    ROLE,

    FOOD,
    CART,
    CATEGORY;

    @JsonCreator
    public static PermissionType forValue(String value) {
        for(PermissionType column : PermissionType.values()) {
            if(column.toValue().equals(value)) {
                return column;
            }
        }
        return null;
    }

    @JsonValue
    public String toValue(){
        return name();
    }
}
