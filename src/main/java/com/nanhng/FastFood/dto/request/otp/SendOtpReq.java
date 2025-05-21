package com.nanhng.FastFood.dto.request.otp;

import com.nanhng.FastFood.entity.otp.constants.OtpPurpose;
import com.nanhng.FastFood.entity.otp.constants.OtpType;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SendOtpReq {
    String phone;
    String email;
    @NotNull
    OtpPurpose purpose;
    @NotNull
    OtpType type;

    @AssertTrue(message = "Either phone or email must be provided")
    public boolean isValid() {
        return phone != null || email != null;
    }
}
