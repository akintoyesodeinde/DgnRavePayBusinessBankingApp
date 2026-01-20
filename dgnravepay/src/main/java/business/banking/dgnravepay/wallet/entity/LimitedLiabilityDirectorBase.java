package business.banking.dgnravepay.wallet.entity;


import business.banking.dgnravepay.wallet.enums.UtilityDocumentType;
import business.banking.dgnravepay.wallet.enums.Nationality;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@MappedSuperclass
@Getter
@Setter
public abstract class LimitedLiabilityDirectorBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected String firstName;
    protected String lastName;
    protected String email;
    protected String phoneNumber;
    protected String percentageOwned;
    protected LocalDate dateOfBirth;
    protected String houseAddress;

    @Enumerated(EnumType.STRING)
    protected Nationality nationality;

    @Enumerated(EnumType.STRING)
    protected UtilityDocumentType utilityDocumentType;

    protected String documentNumber;

    @Column(columnDefinition = "bytea")
    protected byte[] proofOfIdentity;

    @Column(columnDefinition = "bytea")
    protected byte[] proofOfAddress;

    @Column(columnDefinition = "bytea")
    protected byte[] proofOfSignature;
}
