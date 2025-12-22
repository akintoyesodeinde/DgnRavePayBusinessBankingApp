package business.banking.dgnravepay.auth.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private UUID id;

    private String phoneNumber;

    private String referenceId;

    private String deviceFingerprint;

    private boolean used;

    private int resendCount;

    private Instant createdAt;
}