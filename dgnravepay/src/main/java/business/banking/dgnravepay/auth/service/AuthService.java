package business.banking.dgnravepay.auth.service;

import business.banking.dgnravepay.auth.client.DojahOtpClient;
import business.banking.dgnravepay.auth.client.ErrandPayClient;
import business.banking.dgnravepay.auth.config.AppProperties;
import business.banking.dgnravepay.auth.dto.*;
import business.banking.dgnravepay.auth.entity.*;
import business.banking.dgnravepay.auth.repository.DeviceTrustRepository;
import business.banking.dgnravepay.auth.repository.LoginUserRepository;
import business.banking.dgnravepay.auth.repository.RefreshTokenRepository;
import business.banking.dgnravepay.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepo;
    private final DeviceTrustRepository deviceTrustRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final RefreshTokenRepository refreshTokenRepo;
    private final AppProperties props;
    private final ErrandPayClient errandPayClient;
    private final LoginUserRepository loginUserRepository;


    public LoginResponseDto loginWithoutOtp(LoginRequest req) {

        LoginResponseDto response =
                errandPayClient.login(req.getEmail(), req.getPassword());

        LoginUser audit = new LoginUser();
        audit.setUserId(response.getId());          // external user ID
        audit.setFirstName(response.getFirstName());
        audit.setLastName(response.getLastName());
        audit.setUserName(response.getUserName());
        audit.setEmail(response.getEmail());
        audit.setPhoneNumber(response.getPhoneNumber());
        audit.setStatus(response.getStatus());
        audit.setRoleCode(response.getRoleCode());
        audit.setReferalCode(response.getReferalCode());
        audit.setUserReferalCode(response.getUserReferalCode());
        audit.setCountryCode(response.getCountryCode());
        audit.setIsPINSet(response.getIsPINSet());
        audit.setIsKycCompleted(response.getIsKycCompleted());
        audit.setAccessToken(response.getAccessToken());
        audit.setTokenType(response.getTokenType());
        audit.setExpiresIn(response.getExpiresIn());
        audit.setRefreshToken(response.getRefreshToken());
        audit.setAgentCode(response.getAgentCode());
        audit.setIsEmailConfirmed(response.getIsEmailConfirmed());
        audit.setIsPhoneNumberConfirmed(response.getIsPhoneNumberConfirmed());
        audit.setLga(response.getLga());
        audit.setAddress(response.getAddress());
        audit.setState(response.getState());
        audit.setIsSecurityQuestionSet(response.getIsSecurityQuestionSet());
        audit.setIsPushNotificationSet(response.getIsPushNotificationSet());
        audit.setIsDeviceTokenValidated(response.getIsDeviceTokenValidated());
        audit.setIsDefaultPassword(response.getIsDefaultPassword());
        audit.setTierCode(response.getTierCode());
        audit.setIsKycSentToThirdParty(response.getIsKycSentToThirdParty());
        audit.setIsPoolType(response.getIsPoolType());
        audit.setCategory(response.getCategory());
        audit.setActivePendingLien(response.getActivePendingLien());
        audit.setBankName(response.getBankName());
        audit.setUplineName(response.getUplineName());
        audit.setName(response.getName());
        audit.setReferenceId(response.getReferenceId());

        loginUserRepository.save(audit);

        return response;
    }








//    public LoginResponseDto loginWithoutOtp(LoginRequest req) {
//
//        LoginResponseDto response =
//                errandPayClient.login(req.getEmail(), req.getPassword());
//
//        LoginUser audit = new LoginUser();
//        audit.setUserId(response.getId());
//        audit.setFirstName(response.getFirstName());
//        audit.setLastName(response.getLastName());
//        audit.setUserName(response.getUserName());
//        audit.setEmail(response.getEmail());
//        audit.setPhoneNumber(response.getPhoneNumber());
//        audit.setStatus(response.getStatus());
//        audit.setRoleCode(response.getRoleCode());
//        audit.setReferalCode(response.getReferalCode());
//        audit.setUserReferalCode(response.getUserReferalCode());
//        audit.setCountryCode(response.getCountryCode());
//        audit.setIsPINSet(response.getIsPINSet());
//        audit.setIsKycCompleted(response.getIsKycCompleted());
//        audit.setAccessToken(response.getAccessToken());
//        audit.setTokenType(response.getTokenType());
//        audit.setExpiresIn(response.getExpiresIn());
//        audit.setRefreshToken(response.getRefreshToken());
//        audit.setAgentCode(response.getAgentCode());
//        audit.setIsEmailConfirmed(response.getIsEmailConfirmed());
//        audit.setIsPhoneNumberConfirmed(response.getIsPhoneNumberConfirmed());
//        audit.setLga(response.getLga());
//        audit.setAddress(response.getAddress());
//        audit.setState(response.getState());
//        audit.setIsSecurityQuestionSet(response.getIsSecurityQuestionSet());
//        audit.setIsPushNotificationSet(response.getIsPushNotificationSet());
//        audit.setIsDeviceTokenValidated(response.getIsDeviceTokenValidated());
//        audit.setIsDefaultPassword(response.getIsDefaultPassword());
//        audit.setTierCode(response.getTierCode());
//        audit.setIsKycSentToThirdParty(response.getIsKycSentToThirdParty());
//        audit.setActivePendingLien(response.getActivePendingLien());
//        audit.setBankName(response.getBankName());
//        audit.setUplineName(response.getUplineName());
//        audit.setIsPoolType(response.getIsPoolType());
//        audit.setCategory(response.getCategory());
//
//        loginUserRepository.save(audit);
//
//        return response;
//    }





//    public LoginResponse loginWithoutOtp(PasswordLoginRequest req) {
//        // 1. Fetch ALL users and find the one whose hash matches this raw password
//        // This avoids passing phone numbers in the request
//        UserEntity user = userRepo.findAll().stream()
//                .filter(u -> passwordEncoder.matches(req.getPassword(), u.getPasswordHash()))
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException("Invalid password"));
//
//        // 2. Device Fingerprint and Phone Number Validation
//        DeviceTrustEntity trust = deviceTrustRepo.
//                findByUserIdAndDeviceFingerprintAndPhoneNumber(user.getId(), req.getDeviceFingerprint(), user.getPhoneNumber())
//                .orElseThrow(() -> new IllegalStateException("OTP required: Device not recognized"));
//
//        if (trust.getTrustScore() < 70) {
//            throw new IllegalStateException("OTP required: Trust score too low");
//        }
//
//        // 3. Update trust metadata
//        trust.setLastUsedAt(Instant.now());
//        deviceTrustRepo.save(trust);
//
//        // 4. Issue Tokens
//        String access = jwtService.generateAccessToken(user);
//        String refresh = jwtService.generateRefreshToken(user);
//
//        refreshTokenRepo.save(
//                RefreshToken.builder()
//                        .token(refresh)
//                        .user(user)
//                        .expiryDate(Instant.now().plusSeconds(props.getRefreshTokenExpirationSec()))
//                        .build()
//        );
//
//        return new LoginResponse(access, refresh);
//    }
//




//
//    public LoginResponseDto loginWithoutOtp(LoginRequest req) {
//
//        LoginResponseDto response =
//                errandPayClient.login(req.getEmail(), req.getPassword());
////
//        UserEntity user = userRepo.findByEmail(req.getEmail())
//                .orElseThrow(() -> new IllegalArgumentException("User not found"));
//
//        if (!passwordEncoder.matches(req.getPassword(), user.getPasswordHash())) {
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
//                        new IllegalStateException("OTP required: device not trusted"));
//
//        if (trust.getTrustScore() < 70) {
//            throw new IllegalStateException("OTP required: trust score too low");
//        }
//
//        trust.setLastUsedAt(Instant.now());
//        deviceTrustRepo.save(trust);

//        LoginUser audit = new LoginUser(
//                UUID.randomUUID(),
//                response.getReferenceId(),
//                response.getFirstName(),
//                response.getLastName(),
//                response.getUserName(),
//                response.getEmail(),
//                response.getPhoneNumber(),
//                response.getStatus(),
//                response.getRoleCode(),
//                response.getReferalCode(),
//                response.getUserReferalCode(),
//                response.getCountryCode(),
//                response.getIsPINSet(),
//                response.getIsKycCompleted(),
//                response.getAccessToken(),
//                response.getTokenType(),
//                response.getExpiresIn(),
//                response.getRefreshToken(),
//                response.getAgentCode(),
//                response.getIsEmailConfirmed(),
//                response.getIsPhoneNumberConfirmed(),
//                response.getLga(),
//                response.getAddress(),
//                response.getState(),
//                response.getIsSecurityQuestionSet(),
//                response.getIsPushNotificationSet(),
//                response.getIsDeviceTokenValidated(),
//                response.getIsDefaultPassword(),
//                response.getTierCode(),
//                response.getIsKycSentToThirdParty()
//        );
//
//        loginUserRepository.save(audit);
//
//        return response;
//    }















//
//    public void logout(String authHeader) {
//        String token = authHeader.replace("Bearer ", "");
//        refreshTokenRepo.deleteByToken(token);
//    }

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
