package business.banking.dgnravepay.wallet.entity;




import business.banking.dgnravepay.wallet.enums.BusinessRegistrationType;
import jakarta.persistence.*;
import lombok.*;



@Entity
@Table(name = "sole_proprietorship")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SoleProprietorship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "user_proprietor_id", nullable = false, unique = true)
    private UserProprietor userProprietor;

    @Column(nullable = false)
    private String legalName;

    @Column(nullable = false, unique = true)
    private String registrationNumberBn;

    private String businessAccountName;

    @Enumerated(EnumType.STRING)
    private BusinessRegistrationType businessRegistrationType;

    @Column(columnDefinition = "bytea")
    private byte[] certificateApplicationForm;

    @Column(columnDefinition = "bytea")
    private byte[] certificateOfBusinessName;
}
