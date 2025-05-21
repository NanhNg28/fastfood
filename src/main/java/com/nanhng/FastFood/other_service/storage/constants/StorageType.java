package com.nanhng.FastFood.other_service.storage.constants;

import com.fasterxml.jackson.annotation.JsonValue;
import com.nanhng.FastFood.dto.constant.BaseEnum;

public enum StorageType implements BaseEnum<Integer> {
    STORAGE_NFS_LOCAL;

    public static StorageType getType(int index) {
        return StorageType.values()[index];
    }
    public static StorageType getType(String name) {
        return StorageType.valueOf(name);
    }

    @JsonValue
    public Integer toValue() {
        return ordinal();
    }
}
