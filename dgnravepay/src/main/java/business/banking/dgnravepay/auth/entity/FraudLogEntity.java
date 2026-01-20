package business.banking.dgnravepay.auth.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "fraud_logs")
@Getter
@Setter
public class FraudLogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false)
    private UUID id;
    private String phoneNumber;
    private String deviceFingerprint;
    private String reason;
    private Instant createdAt;
}
