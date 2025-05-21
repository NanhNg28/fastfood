package com.nanhng.FastFood.entity.upload_file.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.nanhng.FastFood.dto.constant.BaseEnum;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(type = "integer")
public enum UploadFileType implements BaseEnum<Integer> {
    IMAGE(0);

    private final int value;

    UploadFileType(int value) {
        this.value = value;
    }

    @JsonCreator
    public static UploadFileType of(int value) {
        for(UploadFileType item : UploadFileType.values()) {
            if(item.toValue() == value){
                return item;
            }
        }
        return null;
    }

    @JsonValue
    public Integer toValue() {
        return value;
    }
}
