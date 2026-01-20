package business.banking.dgnravepay.wallet.dto;



import business.banking.dgnravepay.wallet.entity.UserProprietor;
import business.banking.dgnravepay.wallet.enums.BusinessRegistrationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Data
public class SpSubmitConfirmedDetailsDto {
    private Long userProprietorId;
    private UserProprietor userProprietor;
    private String legalName;
    private String registrationNumberBn;
    private String businessAccountName;
    private BusinessRegistrationType businessRegistrationType;
    private byte[] certificateApplicationForm;
    private byte[] certificateOfBusinessName;
}
