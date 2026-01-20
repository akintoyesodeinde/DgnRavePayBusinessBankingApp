package business.banking.dgnravepay.wallet.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LimitedPreConfirmDto {
    private String legalName;
    private String registrationNumberRc;
    private String businessAccountName;
    private String taxIdentificationNumber;
    private Boolean memorandumUploaded;
    private Boolean cac3Uploaded;
    private Boolean cac2Uploaded;
    private Boolean cac7Uploaded;
    private Boolean incorporationUploaded;
    private Boolean boardResolutionUploaded;
}
