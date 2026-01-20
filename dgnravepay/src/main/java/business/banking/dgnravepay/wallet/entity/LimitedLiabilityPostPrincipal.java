package business.banking.dgnravepay.wallet.entity;


import business.banking.dgnravepay.wallet.enums.DocumentType;
import business.banking.dgnravepay.wallet.enums.Nationality;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "limited_liability_post_principal")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LimitedLiabilityPostPrincipal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "limited_liability_post_id", nullable = false)
    private LimitedLiabilityPost limitedLiabilityPost;

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String percentageOwned;
    private LocalDate dateOfBirth;
    private String houseAddress;

    @Enumerated(EnumType.STRING)
    private Nationality nationality;

    @Enumerated(EnumType.STRING)
    private DocumentType documentType;

    private String documentNumber;

    @Column(columnDefinition = "bytea")
    private byte[] proofOfIdentity;

    @Column(columnDefinition = "bytea")
    private byte[] proofOfAddress;

    @Column(columnDefinition = "bytea")
    private byte[] proofOfSignature;
}