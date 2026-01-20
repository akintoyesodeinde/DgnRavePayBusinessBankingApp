package business.banking.dgnravepay.wallet.dto;


import business.banking.dgnravepay.wallet.enums.Nationality;
import lombok.Getter;
import lombok.Setter;
import business.banking.dgnravepay.wallet.enums.DocumentType;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class NgoDirectorRequestDto {

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
