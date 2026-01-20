package business.banking.dgnravepay.auth.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;



@Entity
@Table(name = "login_user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginUser {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Column(columnDefinition = "TEXT")
    private String referenceId;

    @Column(columnDefinition = "TEXT")
    private String userId;

    @Column(columnDefinition = "TEXT")
    private String firstName;

    @Column(columnDefinition = "TEXT")
    private String lastName;

    @Column(columnDefinition = "TEXT")
    private String userName;

    @Column(columnDefinition = "TEXT")
    private String email;

    @Column(columnDefinition = "TEXT")
    private String phoneNumber;

    @Column(columnDefinition = "TEXT")
    private String status;

    @Column(columnDefinition = "TEXT")
    private String roleCode;

    @Column(columnDefinition = "TEXT")
    private String referalCode;

    @Column(columnDefinition = "TEXT")
    private String userReferalCode;

    @Column(columnDefinition = "TEXT")
    private String countryCode;

    private Boolean isPINSet;

    private Boolean isKycCompleted;

    @Column(columnDefinition = "TEXT")
    private String accessToken;

    @Column(columnDefinition = "TEXT")
    private String tokenType;

    private LocalDateTime expiresIn;

    @Column(columnDefinition = "TEXT")
    private String refreshToken;

    @Column(columnDefinition = "TEXT")
    private String agentCode;

    private Boolean isEmailConfirmed;

    private Boolean isPhoneNumberConfirmed;

    @Column(columnDefinition = "TEXT")
    private String lga;

    @Column(columnDefinition = "TEXT")
    private String address;

    @Column(columnDefinition = "TEXT")
    private String state;

    private Boolean isSecurityQuestionSet;

    private Boolean isPushNotificationSet;

    private Boolean isDeviceTokenValidated;

    private Boolean isDefaultPassword;

    @Column(columnDefinition = "TEXT")
    private String tierCode;

    private Boolean isKycSentToThirdParty;

    private Boolean isPoolType;

    @Column(columnDefinition = "TEXT")
    private String category;

    private Boolean activePendingLien;

    @Column(columnDefinition = "TEXT")
    private String bankName;

    @Column(columnDefinition = "TEXT")
    private String uplineName;

    @Column(columnDefinition = "TEXT")
    private String name;
}
