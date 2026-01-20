package business.banking.dgnravepay.auth.service;



import business.banking.dgnravepay.auth.client.DojahOtpClient;
import business.banking.dgnravepay.auth.dto.SendOtpRequestDto;
import business.banking.dgnravepay.auth.dto.SendOtpResponseDto;
import business.banking.dgnravepay.auth.dto.ValidateOtpRequestDto;
import business.banking.dgnravepay.auth.entity.DeviceTrustEntity;
import business.banking.dgnravepay.auth.entity.OtpRequestEntity;
import business.banking.dgnravepay.auth.entity.UserEntity;
import business.banking.dgnravepay.auth.repository.DeviceTrustRepository;
import business.banking.dgnravepay.auth.repository.OtpRequestRepository;
import business.banking.dgnravepay.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class OtpService {
    private final DojahOtpClient dojahClient;
    private final OtpRequestRepository otpRepo;
    private final UserRepository userRepo;
    private final OtpCacheService otpCacheService;
    private final OtpRateLimiter rateLimiter;
    private final DeviceTrustRepository deviceTrustRepo;
    private final PasswordEncoder passwordEncoder;



    public SendOtpResponseDto sendOtp(SendOtpRequestDto dto) {
        // 1. Attempt to send via Dojah Client first
        SendOtpResponseDto dojahResponse = dojahClient.sendOtp(dto.getPhoneNumber());

        // 2. Status Check: Dojah returns "sent" or "success" on success
        boolean isSuccessful = "sent".equalsIgnoreCase(dojahResponse.getStatus()) ||
                "success".equalsIgnoreCase(dojahResponse.getStatus());

        if (!isSuccessful) {
            throw new RuntimeException("OTP failed to send via provider. Status: " + dojahResponse.getStatus());
        }

        // 3. Save to Database ONLY if step 2 passed
        OtpRequestEntity otp = new OtpRequestEntity();
        otp.setId(UUID.randomUUID());
        otp.setPhoneNumber(dto.getPhoneNumber());
        otp.setReferenceId(dojahResponse.getReferenceId());
        otp.setDeviceFingerprint(dto.getDeviceFingerprint());
        otp.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        otp.setFirstName(dto.getFirstName());
        otp.setLastName(dto.getLastName());
        otp.setEmail(dto.getEmail());
        otp.setUsed(false);
        otp.setCreatedAt(Instant.now());

        otpRepo.save(otp);

        return dojahResponse;
    }





    public boolean validateOtp(ValidateOtpRequestDto dto) {

        // Fetch OTP request
        OtpRequestEntity otp = otpRepo.findByReferenceId(dto.getReferenceId())
                .orElseThrow(() -> new IllegalArgumentException("OTP not found"));

        if (otp.isUsed()) {
            throw new IllegalStateException("OTP already used");
        }

        // Validate OTP with Dojah
        boolean valid = dojahClient.validateOtp(
                dto.getReferenceId(),
                dto.getCode()
        );

        if (!valid) {
            return false; // <-- return false instead of throwing
        }

        // Mark OTP as used
        otp.setUsed(true);
        otpRepo.save(otp);

        // Create or update user
        UserEntity user = userRepo.findByPhoneNumber(otp.getPhoneNumber())
                .orElseGet(() -> {
                    UserEntity u = new UserEntity();
                    u.setId(UUID.randomUUID());
                    u.setPhoneNumber(otp.getPhoneNumber());
                    u.setDeviceFingerprint(otp.getDeviceFingerprint());
                    u.setCreatedAt(Instant.now());
                    u.setEmail(otp.getEmail());
                    u.setFirstName(otp.getFirstName());
                    u.setLastName(otp.getLastName());
                    u.setPasswordHash(otp.getPasswordHash());
                    return u;
                });

        user.setVerified(true);
        user.setDeviceFingerprint(otp.getDeviceFingerprint());
        userRepo.save(user);

        // DEVICE TRUST SCORE UPDATE / CREATE
        deviceTrustRepo
                .findByUserIdAndDeviceFingerprintAndPhoneNumber(
                        user.getId(),
                        otp.getDeviceFingerprint(),
                        otp.getPhoneNumber()
                )
                .ifPresentOrElse(
                        trust -> {
                            trust.setTrustScore(Math.min(trust.getTrustScore() + 10, 100));
                            trust.setLastUsedAt(Instant.now());
                            deviceTrustRepo.save(trust);
                        },
                        () -> {
                            DeviceTrustEntity trust = new DeviceTrustEntity();
                            trust.setId(UUID.randomUUID());
                            trust.setUserId(user.getId());
                            trust.setPhoneNumber(otp.getPhoneNumber());
                            trust.setDeviceFingerprint(otp.getDeviceFingerprint());
                            trust.setTrustScore(100);
                            trust.setLastUsedAt(Instant.now());
                            deviceTrustRepo.save(trust);
                        }
                );

        return true;   // <-- SUCCESS RESULT
    }
}



