package business.banking.dgnravepay.auth.controller;

import business.banking.dgnravepay.auth.dto.*;
import business.banking.dgnravepay.auth.service.AuthService;
import business.banking.dgnravepay.auth.service.OtpResendService;
import business.banking.dgnravepay.auth.service.OtpService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final OtpResendService otpResendService;
    private final AuthService authService;
    private final OtpService otpService;

//
//    @PostMapping("/login")
//    public LoginResponse login(@RequestBody PasswordLoginRequest request) {
//        return authService.loginWithoutOtp(request);
//    }



    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequest request) {
        return authService.loginWithoutOtp(request);
    }


//
//    @PostMapping("/logout")
//    public void logout(@RequestHeader("Authorization") String authHeader) {
//        authService.logout(authHeader);
//    }



    @PostMapping("/send-otp")
    public SendOtpResponseDto sendOtp(@RequestBody SendOtpRequestDto dto) {
        return otpService.sendOtp(dto);
    }

    @PostMapping("/validate-otp")
    public ValidateOtpResponseDto validateOtp(@RequestBody ValidateOtpRequestDto dto) {

        boolean valid = otpService.validateOtp(dto);

        if (!valid) {
            return new ValidateOtpResponseDto(false, "Invalid OTP");
        }

        return new ValidateOtpResponseDto(true, "OTP Validated successfully");
    }



    @PostMapping("/resend-otp")
    public SendOtpResponseDto resendOtp(@RequestBody ResendOtpRequestDto dto) {
        return otpResendService.resendOtp(dto);
    }
}
