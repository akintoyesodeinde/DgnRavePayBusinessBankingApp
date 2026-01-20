package business.banking.dgnravepay.wallet.entity;

import business.banking.dgnravepay.wallet.enums.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Entity
@Table(name = "user_proprietor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProprietor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String bvn;

    @Column(nullable = false)
    private String nin;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String occupation;

    @Enumerated(EnumType.STRING)
    private Nationality nationality;

    private Boolean politicallyExposedPerson;


    @Column(nullable = false)
    private String businessName;

    @Enumerated(EnumType.STRING)
    private IndustryCategory industryCategory;

    @Enumerated(EnumType.STRING)
    private IndustrySubCategory industrySubCategory;

    @Enumerated(EnumType.STRING)
    private StaffSize staffSize;

    @Enumerated(EnumType.STRING)
    private SourceOfFund sourceOfFund;

    @Enumerated(EnumType.STRING)
    private PurposeOfAccount purposeOfAccount;

    /* ADDRESS (CSC DATA) */
    private String country;      // Nigeria
    private String state;        // Lagos
    private String stateIso2;    // LA
    private String city;         // Ikeja

    @Column(columnDefinition = "bytea")
    private byte[] companyLogo;

    @Column(columnDefinition = "bytea")
    private byte[] utilityBill;

    @Enumerated(EnumType.STRING)
    private UtilityDocumentType utilityBillType;
}
