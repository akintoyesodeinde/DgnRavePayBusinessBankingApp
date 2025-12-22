package business.banking.dgnravepay.auth.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private UUID id;

    private String phoneNumber;
    private String deviceFingerprint;
    private String reason;
    private Instant createdAt;
}
