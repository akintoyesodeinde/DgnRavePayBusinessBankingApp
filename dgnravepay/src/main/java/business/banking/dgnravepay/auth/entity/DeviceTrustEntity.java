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
@Table(name = "device_trust")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeviceTrustEntity {
    @Id
    private UUID id;

    private UUID userId;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String deviceFingerprint;

    private int trustScore;

    private Instant lastUsedAt;
}

