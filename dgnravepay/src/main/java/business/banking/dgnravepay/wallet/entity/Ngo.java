package business.banking.dgnravepay.wallet.entity;


import business.banking.dgnravepay.wallet.enums.BusinessRegistrationType;
import jakarta.persistence.*;
import lombok.*;




@Entity
@Table(name = "ngo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ngo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_proprietor_id", nullable = false)
    private UserProprietor userProprietor;

    @Column(nullable = false)
    private String legalName;

    @Column(nullable = false, unique = true)
    private String registrationNumberIt;

    private String businessAccountName;

    @Enumerated(EnumType.STRING)
    private BusinessRegistrationType businessRegistrationType;

    @Column(columnDefinition = "bytea")
    private byte[] cacIt1Form;
}
