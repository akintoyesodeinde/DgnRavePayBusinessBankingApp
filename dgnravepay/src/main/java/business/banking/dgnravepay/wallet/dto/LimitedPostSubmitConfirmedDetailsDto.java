package business.banking.dgnravepay.wallet.dto;


import business.banking.dgnravepay.wallet.entity.UserProprietor;
import business.banking.dgnravepay.wallet.enums.BusinessOwnershipStructure;
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
public class LimitedPostSubmitConfirmedDetailsDto {
    private Long userProprietorId;
    private UserProprietor userProprietor;
    private String legalName;
    private String registrationNumberRc;
    private String businessAccountName;
    private BusinessRegistrationType businessRegistrationType;
    private BusinessOwnershipStructure businessOwnershipStructure;
    private String taxIdentificationNumber;
    private byte[] memorandumOfAssociation;
    private byte[] certificateOfIncorporation;

    private byte[] boardResolution;

}
