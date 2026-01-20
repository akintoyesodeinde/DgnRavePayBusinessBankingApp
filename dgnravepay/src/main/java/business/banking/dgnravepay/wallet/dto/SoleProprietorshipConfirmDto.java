package business.banking.dgnravepay.wallet.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SoleProprietorshipConfirmDto {

    private String legalName;
    private String registrationNumberBn;
    private String businessAccountName;
    private Boolean certificateApplicationUploaded;
    private Boolean certificateBusinessNameUploaded;
}
