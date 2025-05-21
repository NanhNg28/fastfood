package com.nanhng.FastFood.entity.role.constant;

import com.nanhng.FastFood.dto.constant.BaseEnum;

public enum PermissionGroup implements BaseEnum<String> {
    STATISTIC,
    CONFIG,
    FEATURE;


    @Override
    public String toValue() {
        return name();
    }
}
