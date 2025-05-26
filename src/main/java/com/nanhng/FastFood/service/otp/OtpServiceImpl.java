package com.nanhng.FastFood.service.otp;

import com.nanhng.FastFood.dto.request.otp.SendOtpReq;
import com.nanhng.FastFood.dto.response.otp.SendOtpRes;
import com.nanhng.FastFood.service.repository.otp.OtpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OtpServiceImpl implements OtpService {
    private final OtpRepository otpRepository;

    @Override
    public SendOtpRes sendOtp(SendOtpReq request) {
        return null;
    }
}
