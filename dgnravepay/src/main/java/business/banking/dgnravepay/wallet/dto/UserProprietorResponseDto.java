package business.banking.dgnravepay.wallet.dto;

import business.banking.dgnravepay.wallet.enums.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class UserProprietorResponseDto {

    private Long id;
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

    private String country;
    private String state;
    private String stateIso2;
    private String city;
}
