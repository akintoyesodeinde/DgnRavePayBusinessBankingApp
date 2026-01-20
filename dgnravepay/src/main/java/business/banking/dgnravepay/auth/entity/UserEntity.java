package business.banking.dgnravepay.auth.entity;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    private String passwordHash;

    private boolean verified;

    private Instant createdAt;

    // Last successfully verified device
    private String deviceFingerprint;
    private String firstName;
    private String lastName;
    private String email;
}
