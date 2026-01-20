package business.banking.dgnravepay.wallet.dto;


import business.banking.dgnravepay.wallet.enums.UtilityDocumentType;
import business.banking.dgnravepay.wallet.enums.Nationality;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DirectorRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String percentageOwned;
    private LocalDate dateOfBirth;
    private String houseAddress;
    private Nationality nationality;
    private UtilityDocumentType utilityDocumentType;
    private String documentNumber;
}
