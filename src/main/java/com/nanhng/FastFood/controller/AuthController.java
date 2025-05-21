package com.nanhng.FastFood.controller;

import com.nanhng.FastFood.dto.request.otp.SendOtpReq;
import com.nanhng.FastFood.dto.request.user.UserChangePasswordReq;
import com.nanhng.FastFood.dto.request.user.UserLoginReq;
import com.nanhng.FastFood.dto.request.user.UserRegisterReq;
import com.nanhng.FastFood.dto.response.BaseResponse;
import com.nanhng.FastFood.dto.response.otp.SendOtpRes;
import com.nanhng.FastFood.dto.response.user.UserDetailRes;
import com.nanhng.FastFood.entity.user.User;
import com.nanhng.FastFood.service.otp.OtpService;
import com.nanhng.FastFood.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class AuthController {

    private final UserService userService;
    private final OtpService otpService;

    @Operation(description = "login")
    @PostMapping("v1/auth/login")
    public ResponseEntity<BaseResponse<UserDetailRes>>LoginUser(@RequestBody @Valid UserLoginReq request){
        UserDetailRes user = userService.loginUser(request);
        return ResponseEntity.ok(new BaseResponse<>(user,"Login successful"));
    }

    @Operation(description = "register")
    @PostMapping("v1/auth/register")
    public ResponseEntity<BaseResponse<UserDetailRes>> registerUser(@RequestBody @Valid UserRegisterReq request){
        UserDetailRes user = userService.registerUser(request);
        return ResponseEntity.ok(new BaseResponse<>(user,"Register successful"));
    }

    @Operation(description = "forgot password")
    @PostMapping("v1/auth/forgot-password")
    public ResponseEntity<BaseResponse<User>> changePassword(@RequestBody @Valid UserForgotPasswordReq request){
        return ResponseEntity.ok(new BaseResponse<>(userService.forgotPassword(request),"change user`s password successfully"));
    }

    @Operation(description = "send OTP")
    @PostMapping("v1/auth/send-otp")
    public ResponseEntity<BaseResponse<SendOtpRes>> sendOtp(@RequestBody @Valid SendOtpReq request){
        return ResponseEntity.ok(new BaseResponse<>(otpService.sendOtp(request),"sendOtp successfully"));
    }

}
