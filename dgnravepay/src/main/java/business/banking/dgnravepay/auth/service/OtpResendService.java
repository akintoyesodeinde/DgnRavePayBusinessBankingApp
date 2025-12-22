package business.banking.dgnravepay.auth.service;


import business.banking.dgnravepay.auth.client.DojahOtpClient;
import business.banking.dgnravepay.auth.entity.OtpRequestEntity;
import business.banking.dgnravepay.auth.repository.OtpRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OtpResendService {

    private static final int MAX_RESEND = 3;

    private final DojahOtpClient dojahClient;
    private final OtpRequestRepository otpRepo;
    private final OtpCacheService otpCacheService;
    private final OtpRateLimiter rateLimiter;

    public void resendOtp(String phoneNumber, String deviceFingerprint) {

        rateLimiter.checkLimit(phoneNumber);

        OtpRequestEntity lastOtp = otpRepo
                .findTopByPhoneNumberOrderByCreatedAtDesc(phoneNumber)
                .orElseThrow(() -> new IllegalStateException("No OTP request found"));

        if (lastOtp.getResendCount() >= MAX_RESEND) {
            throw new IllegalStateException("OTP resend limit exceeded");
        }

        // Invalidate old OTP
        otpCacheService.delete(lastOtp.getReferenceId());

        // Send new OTP
        String newReferenceId = dojahClient.sendOtp(phoneNumber);

        OtpRequestEntity otp = new OtpRequestEntity();
        otp.setId(UUID.randomUUID());
        otp.setPhoneNumber(phoneNumber);
        otp.setReferenceId(newReferenceId);
        otp.setDeviceFingerprint(deviceFingerprint);
        otp.setUsed(false);
        otp.setResendCount(lastOtp.getResendCount() + 1);
        otp.setCreatedAt(Instant.now());

        otpRepo.save(otp);

        otpCacheService.saveOtp(newReferenceId, Duration.ofMinutes(10));
    }
}
