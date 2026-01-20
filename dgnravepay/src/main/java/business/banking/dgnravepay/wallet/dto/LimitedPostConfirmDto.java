package business.banking.dgnravepay.wallet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
public class LimitedPostConfirmDto {
    private String legalName;
    private String registrationNumberRc;
    private String businessAccountName;
    private String taxIdentificationNumber;
    private Boolean memorandumUploaded;
    private Boolean incorporationUploaded;
    private Boolean boardResolutionUploaded;
}