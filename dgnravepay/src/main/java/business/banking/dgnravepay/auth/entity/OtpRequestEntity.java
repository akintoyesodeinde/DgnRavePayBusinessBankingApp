package business.banking.dgnravepay.auth.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;



@Entity
@Table(name = "otp_requests")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OtpRequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false)
    private UUID id;

    private String phoneNumber;

    private String referenceId;

    private String deviceFingerprint;

    private boolean used;

    private int resendCount;

    private Instant createdAt;

    private String firstName;

    private String lastName;

    private String email;

    private String passwordHash;
}