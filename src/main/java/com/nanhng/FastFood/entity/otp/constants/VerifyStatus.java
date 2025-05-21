package com.nanhng.FastFood.entity.otp.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.nanhng.FastFood.dto.constant.BaseEnum;
import io.swagger.v3.oas.annotations.media.Schema;

public enum VerifyStatus implements BaseEnum<Integer> {
    @Schema(description = "Đợi xác thực OTP")
    VERIFY_PENDING(0),
    @Schema(description = "Xác thực thành công")
    VERIFIED(1),
    @Schema(description = "Xác thực OTP thất bại")
    FAILED(2),
    @Schema(description = "Hết hạn sử dụng OTP")
    EXPIRED(3),
    @Schema(description = "Có thêm phiên xác thực mới của SĐT này")
    OTHER(4);

    final int value;

    VerifyStatus(int value) {
        this.value = value;
    }

    @JsonCreator
    public static VerifyStatus fromValue(int value) {
        for (VerifyStatus column : VerifyStatus.values()) {
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
