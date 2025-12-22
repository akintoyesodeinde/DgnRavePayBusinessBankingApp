package business.banking.dgnravepay.auth.service;

import business.banking.dgnravepay.auth.config.AppProperties;
import business.banking.dgnravepay.auth.dto.LoginResponse;
import business.banking.dgnravepay.auth.dto.PasswordLoginRequest;
import business.banking.dgnravepay.auth.entity.DeviceTrustEntity;
import business.banking.dgnravepay.auth.entity.RefreshToken;
import business.banking.dgnravepay.auth.entity.UserEntity;
import business.banking.dgnravepay.auth.repository.DeviceTrustRepository;
import business.banking.dgnravepay.auth.repository.RefreshTokenRepository;
import business.banking.dgnravepay.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepo;
    private final DeviceTrustRepository deviceTrustRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final RefreshTokenRepository refreshTokenRepo;
    private final AppProperties props;

    public LoginResponse loginWithoutOtp(PasswordLoginRequest req) {
        // 1. Fetch ALL users and find the one whose hash matches this raw password
        // This avoids passing phone numbers in the request
        UserEntity user = userRepo.findAll().stream()
                .filter(u -> passwordEncoder.matches(req.getPassword(), u.getPasswordHash()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid password"));

        // 2. Device Fingerprint and Phone Number Validation
        DeviceTrustEntity trust = deviceTrustRepo.
                findByUserIdAndDeviceFingerprintAndPhoneNumber(user.getId(), req.getDeviceFingerprint(), user.getPhoneNumber())
                .orElseThrow(() -> new IllegalStateException("OTP required: Device not recognized"));

        if (trust.getTrustScore() < 70) {
            throw new IllegalStateException("OTP required: Trust score too low");
        }

        // 3. Update trust metadata
        trust.setLastUsedAt(Instant.now());
        deviceTrustRepo.save(trust);

        // 4. Issue Tokens
        String access = jwtService.generateAccessToken(user);
        String refresh = jwtService.generateRefreshToken(user);

        refreshTokenRepo.save(
                RefreshToken.builder()
                        .token(refresh)
                        .user(user)
                        .expiryDate(Instant.now().plusSeconds(props.getRefreshTokenExpirationSec()))
                        .build()
        );

        return new LoginResponse(access, refresh);
    }


    public void logout(String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        refreshTokenRepo.deleteByToken(token);
    }

}















//package business.banking.dgnravepay.auth.service;
//
//import business.banking.dgnravepay.auth.dto.LoginResponse;
//import business.banking.dgnravepay.auth.dto.PasswordLoginRequest;
//import business.banking.dgnravepay.auth.entity.DeviceTrustEntity;
//import business.banking.dgnravepay.auth.entity.RefreshToken;
//import business.banking.dgnravepay.auth.entity.UserEntity;
//import business.banking.dgnravepay.auth.repository.DeviceTrustRepository;
//import business.banking.dgnravepay.auth.repository.RefreshTokenRepository;
//import business.banking.dgnravepay.auth.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.time.Instant;
//
//
//@Service
//@RequiredArgsConstructor
//public class AuthService {
//    private final UserRepository userRepo;
//    private final DeviceTrustRepository deviceTrustRepo;
//    private final PasswordEncoder passwordEncoder;
//    private final JwtService jwtService;
//    private final RefreshTokenRepository refreshTokenRepo;
//    private final FraudLogService fraudLogService;
//
//    public LoginResponse loginWithoutOtp(PasswordLoginRequest req) {
//
//        UserEntity user = userRepo.findByPhoneNumber(req.getPhoneNumber())
//                .orElseThrow(() -> new IllegalArgumentException("User not found"));
//
//        if (!passwordEncoder.matches(req.getPassword(), user.getPasswordHash())) {
//            fraudLogService.logFailedLogin(user.getPhoneNumber(), "BAD_PASSWORD");
//            throw new IllegalArgumentException("Invalid credentials");
//        }
//
//        DeviceTrustEntity trust = deviceTrustRepo
//                .findByUserIdAndDeviceFingerprintAndPhoneNumber(
//                        user.getId(),
//                        req.getDeviceFingerprint(),
//                        user.getPhoneNumber()
//                )
//                .orElseThrow(() ->
//                        new IllegalStateException("OTP required"));
//
//        if (trust.getTrustScore() < 70) {
//            throw new IllegalStateException("OTP required");
//        }
//
//        trust.setLastUsedAt(Instant.now());
//        deviceTrustRepo.save(trust);
//
//        String access = jwtService.generateAccessToken(user);
//        String refresh = jwtService.generateRefreshToken(user);
//
//        refreshTokenRepo.save(
//                RefreshToken.builder()
//                        .token(refresh)
//                        .user(user)
//                        .expiryDate(Instant.now().plusSeconds(1209600))
//                        .build()
//        );
//
//        return new LoginResponse(access, refresh);
//    }
//
//    public void logout(String authHeader) {
//        String token = authHeader.replace("Bearer ", "");
//        refreshTokenRepo.deleteByToken(token);
//    }
//}
