package business.banking.dgnravepay.wallet.dto;


import business.banking.dgnravepay.wallet.enums.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class UserProprietorRequestDto {
    private String firstName;
    private String lastName;
    private String bvn;
    private String nin;
    private String email;
    private String occupation;
    private Nationality nationality;
    private Boolean politicallyExposedPerson;
    private String phoneNumber;
    private String businessName;
    private IndustryCategory industryCategory;
    private IndustrySubCategory industrySubCategory;
    private LocalDate dateOfBirth;
    private StaffSize staffSize;
    private SourceOfFund sourceOfFund;
    private PurposeOfAccount purposeOfAccount;
    private UtilityDocumentType utilityDocumentType;

    private String country;     // NG
    private String state;       // Lagos
    private String stateIso2;   // LA
    private String city;        // Ikeja
}
