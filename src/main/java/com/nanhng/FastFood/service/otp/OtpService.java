package com.nanhng.FastFood.service.otp;

import com.nanhng.FastFood.dto.request.otp.SendOtpReq;
import com.nanhng.FastFood.dto.response.otp.SendOtpRes;

public interface OtpService {
    SendOtpRes sendOtp(SendOtpReq request);
}
