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
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserEntity {
    @Id
    private UUID id;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    private String passwordHash;

    private boolean verified;

    private Instant createdAt;

    // Last successfully verified device
    private String deviceFingerprint;
}
