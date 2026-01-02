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

    private final OtpService otpService;
    private final OtpResendService otpResendService;
    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody PasswordLoginRequest request) {
        return authService.loginWithoutOtp(request);
    }

    @PostMapping("/logout")
    public void logout(@RequestHeader("Authorization") String authHeader) {
        authService.logout(authHeader);
    }



    @PostMapping("/send-otp")
    public SendOtpResponseDto sendOtp(@RequestBody SendOtpRequestDto dto) {
        return otpService.sendOtp(dto);
    }



    @PostMapping("/validate-otp")
    public void validateOtp(@RequestBody ValidateOtpRequestDto dto) {
        otpService.validateOtp(dto);
    }

    @PostMapping("/resend-otp")
    public void resendOtp(@RequestBody ResendOtpRequestDto dto) {
        otpResendService.resendOtp(
                dto.getPhoneNumber(),
                dto.getDeviceFingerprint()
        );
    }
}
