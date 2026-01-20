package business.banking.dgnravepay.wallet.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;


@Entity
@Table(name = "user_wallet_upgrade")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserWalletUpgrade {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false)
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String accountNumber;
    private String bankName;
    private String bvn;
    private String businessName;
    private String businessAddress;
    private String rcNumber;
    private LocalDate dateOfBirth;
    private String tin;
    private String nin;
    private String serialNumber;
    private String subPartnerCode;
    private String terminalId;
    private String agentCategory;
    private String businessCategory;
    private String referenceId;

}
