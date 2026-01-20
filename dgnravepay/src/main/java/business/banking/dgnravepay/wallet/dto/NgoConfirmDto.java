package business.banking.dgnravepay.wallet.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class NgoConfirmDto {

    private String legalName;
    private String registrationNumberIt;
    private String businessAccountName;
    private Boolean cacIt1Uploaded;
}
