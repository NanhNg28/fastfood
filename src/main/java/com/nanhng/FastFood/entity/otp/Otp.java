package com.nanhng.FastFood.entity.otp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nanhng.FastFood.entity.BaseEntity;
import com.nanhng.FastFood.entity.otp.constants.OtpType;
import com.nanhng.FastFood.entity.otp.constants.VerifyStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
@Table(name = "otps")
public class Otp extends BaseEntity {

    String otp;
    String email;
    String phone;

    @Column(name = "attempt_count")
    int attemptCount =3;

    @Column(name = "send_type", columnDefinition = "INT")
    OtpType type;

    @Column(name = "verify_status", columnDefinition = "INT")
    VerifyStatus status;

    boolean deleted;

}
