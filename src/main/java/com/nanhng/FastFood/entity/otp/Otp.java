package com.nanhng.FastFood.entity.otp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nanhng.FastFood.entity.BaseEntity;
import com.nanhng.FastFood.entity.otp.constants.OtpType;
import com.nanhng.FastFood.entity.otp.constants.VerifyStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Otp extends BaseEntity {

    String otp;
    String email;
    String phone;

    @Column(name = "attempt_count")
    int attemptCount =3;

    @Column(name = "send_type", columnDefinition = "INT")
    OtpType type;

    @Column(name = "status", columnDefinition = "INT")
    VerifyStatus status;

    boolean deleted;

}
