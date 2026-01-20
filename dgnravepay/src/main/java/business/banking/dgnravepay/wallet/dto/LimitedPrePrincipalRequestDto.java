package business.banking.dgnravepay.wallet.dto;


import business.banking.dgnravepay.wallet.enums.DocumentType;
import business.banking.dgnravepay.wallet.enums.Nationality;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class LimitedPrePrincipalRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String percentageOwned;
    private LocalDate dateOfBirth;
    private String houseAddress;
    private Nationality nationality;
    private DocumentType documentType;
    private String documentNumber;
}


