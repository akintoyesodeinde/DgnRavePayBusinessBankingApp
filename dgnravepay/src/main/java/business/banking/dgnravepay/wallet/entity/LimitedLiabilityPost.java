package business.banking.dgnravepay.wallet.entity;


import business.banking.dgnravepay.wallet.enums.BusinessOwnershipStructure;
import business.banking.dgnravepay.wallet.enums.BusinessRegistrationType;
import jakarta.persistence.*;
import lombok.*;



@Entity
@Table(name = "limited_liability_post")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LimitedLiabilityPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "user_proprietor_id", nullable = false, unique = true)
    private UserProprietor userProprietor;

    private String legalName;
    private String registrationNumberRc;
    private String businessAccountName;

    @Enumerated(EnumType.STRING)
    private BusinessRegistrationType businessRegistrationType;

    @Enumerated(EnumType.STRING)
    private BusinessOwnershipStructure businessOwnershipStructure;

    private String taxIdentificationNumber;

    @Column(columnDefinition = "bytea")
    private byte[] memorandumOfAssociation;

    @Column(columnDefinition = "bytea")
    private byte[] certificateOfIncorporation;

    @Column(columnDefinition = "bytea")
    private byte[] boardResolution;

}