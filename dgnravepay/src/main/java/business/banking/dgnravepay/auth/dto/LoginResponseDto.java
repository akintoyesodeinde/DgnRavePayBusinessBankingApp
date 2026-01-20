package business.banking.dgnravepay.auth.dto;



import lombok.Data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@Data
public class LoginResponseDto {
    private String id;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String phoneNumber;
    private String status;
    private String roleCode;
    private String referalCode;
    private String userReferalCode;
    private String countryCode;
    private Boolean isPINSet;
    private Boolean isKycCompleted;
    private String accessToken;
    private String tokenType;
    private LocalDateTime expiresIn;
    private String refreshToken;
    private String agentCode;
    private Boolean isEmailConfirmed;
    private Boolean isPhoneNumberConfirmed;
    private String lga;
    private String address;
    private String state;
    private Boolean isSecurityQuestionSet;
    private Boolean isPushNotificationSet;
    private Boolean isDeviceTokenValidated;
    private Boolean isDefaultPassword;
    private String tierCode;
    private Boolean isKycSentToThirdParty;
    private Boolean isPoolType;
    private String category;
    private Boolean activePendingLien;
    private String bankName;
    private String uplineName;
    private String name;
    private String referenceId;
}

