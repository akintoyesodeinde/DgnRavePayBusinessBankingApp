package business.banking.dgnravepay.auth.entity;



import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "refresh_tokens")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class RefreshToken {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true, length = 500)
    private String token;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    private Instant expiryDate;
}
