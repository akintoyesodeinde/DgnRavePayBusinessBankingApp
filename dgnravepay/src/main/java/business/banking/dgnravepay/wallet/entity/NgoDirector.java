package business.banking.dgnravepay.wallet.entity;


import business.banking.dgnravepay.wallet.enums.Nationality;
import jakarta.persistence.*;
import lombok.*;
import business.banking.dgnravepay.wallet.enums.DocumentType;

import java.time.LocalDate;

@Entity
@Table(name = "ngo_director")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NgoDirector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ngo_id", nullable = false)
    private Ngo ngo;

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
