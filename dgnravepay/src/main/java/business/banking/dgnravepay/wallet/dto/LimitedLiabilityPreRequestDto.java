package business.banking.dgnravepay.wallet.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class LimitedLiabilityPreRequestDto {
    private Long userProprietorId;
    private String legalName;
    private String registrationNumberRc;
    private String taxIdentificationNumber;
}
